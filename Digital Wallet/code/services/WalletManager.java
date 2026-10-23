package code.services;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import code.accounts.Account;
import code.accounts.AccountType;
import code.entities.Transaction;
import code.entities.TransactionState;
import code.entities.User;
import code.payments.CardPaymentStrategy;
import code.payments.NetBankingPaymentStrategy;
import code.payments.PaymentStrategy;
import code.payments.UpiPaymentStrategy;
import code.repositories.AccountRepository;
import code.repositories.TransactionRepository;
import code.repositories.UserRepository;

public class WalletManager {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final Map<AccountType, PaymentStrategy> paymentStrategies = new ConcurrentHashMap<>();

    private static final class InstanceHolder {
        private static final WalletManager INSTANCE = new WalletManager();
    }

    public static WalletManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private WalletManager() {
        this.userRepository = new UserRepository();
        this.transactionRepository = new TransactionRepository();
        this.accountRepository = new AccountRepository();
        paymentStrategies.put(AccountType.CREDIT_CARD, new CardPaymentStrategy());
        paymentStrategies.put(AccountType.DEBIT_CARD, new CardPaymentStrategy());
        paymentStrategies.put(AccountType.UPI, new UpiPaymentStrategy());
        paymentStrategies.put(AccountType.NET_BANKING, new NetBankingPaymentStrategy());
    }

    public void registerPaymentStrategy(AccountType accountType, PaymentStrategy strategy) {
        Objects.requireNonNull(accountType, "accountType");
        Objects.requireNonNull(strategy, "strategy");
        paymentStrategies.put(accountType, strategy);
    }

    public User registerUser(UUID userId) {
        User user = new User(userId);
        userRepository.addUser(user);
        return user;
    }

    public void addAccount(UUID userId, Account account) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        accountRepository.addAccount(account);
        user.addAccount(account);
    }

    public void setDefaultAccount(UUID userId, UUID accountId) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        user.setDefaultAccount(accountId);
    }

    public Optional<Account> getDefaultAccount(UUID userId) {
        return userRepository.getUserById(userId).flatMap(User::getDefaultAccount);
    }

    public Optional<User> getUser(UUID userId) {
        return userRepository.getUserById(userId);
    }

    public Optional<Account> getAccount(UUID accountId) {
        return accountRepository.getAccountById(accountId);
    }

    public Transaction transfer(String idempotencyKey, UUID senderAccId, UUID receiverAccId, int amount) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency key must not be blank");
        }
        if (senderAccId == null || receiverAccId == null) {
            throw new IllegalArgumentException("Sender and receiver account IDs must not be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than 0");
        }
        if (senderAccId.equals(receiverAccId)) {
            throw new IllegalArgumentException("Sender and receiver can't be same");
        }

        Optional<Transaction> existingTx = transactionRepository.findByIdempotencyKey(idempotencyKey);
        if (existingTx.isPresent()) {
            return existingTx.get();
        }

        Account sender = accountRepository.getAccountById(senderAccId)
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found: " + senderAccId));
        Account receiver = accountRepository.getAccountById(receiverAccId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found: " + receiverAccId));

        Transaction tx = new Transaction(UUID.randomUUID(), senderAccId, receiverAccId, amount,
                TransactionState.PENDING, Instant.now(), idempotencyKey);

        return processTransfer(sender, receiver, tx);
    }
    
    public Transaction transferPeerToPeer(String idempotencyKey, UUID senderUserId, UUID receiverUserId, int amount) {
        if (senderUserId == null || receiverUserId == null) {
            throw new IllegalArgumentException("Sender and receiver user IDs must not be null");
        }
        if (senderUserId.equals(receiverUserId)) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same user");
        }

        User senderUser = userRepository.getUserById(senderUserId)
                .orElseThrow(() -> new IllegalArgumentException("Sender user not found: " + senderUserId));
        User receiverUser = userRepository.getUserById(receiverUserId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver user not found: " + receiverUserId));

        Account senderAccount = senderUser.getDefaultAccount()
                .orElseThrow(() -> new IllegalStateException("Sender user has no default account configured"));
        Account receiverAccount = receiverUser.getDefaultAccount()
                .orElseThrow(() -> new IllegalStateException("Receiver user has no default account configured"));

        return transfer(idempotencyKey, senderAccount.getId(), receiverAccount.getId(), amount);
    }

    private Transaction processTransfer(Account sender, Account receiver, Transaction tx) {
        Account firstLock = sender.getId().compareTo(receiver.getId()) < 0 ? sender : receiver;
        Account secondLock = sender.getId().compareTo(receiver.getId()) < 0 ? receiver : sender;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                Optional<Transaction> recheckTx = transactionRepository.findByIdempotencyKey(tx.getIdempotencyKey());
                if (recheckTx.isPresent() && recheckTx.get().getState() != TransactionState.PENDING) {
                    return recheckTx.get();
                }

                transactionRepository.save(tx);

                PaymentStrategy senderStrategy = paymentStrategies.get(sender.getType());
                if (senderStrategy == null) {
                    tx.setState(TransactionState.FAILED);
                    transactionRepository.save(tx);
                    throw new IllegalStateException("No payment strategy registered for account type: " + sender.getType());
                }

                PaymentStrategy receiverStrategy = paymentStrategies.getOrDefault(receiver.getType(), senderStrategy);
                boolean isDebited = false;

                try {
                    senderStrategy.authorizeAndDebit(sender, tx.getAmount());
                    isDebited = true;
                    tx.setState(TransactionState.DEBITED);

                    receiverStrategy.credit(receiver, tx.getAmount());
                    tx.setState(TransactionState.SUCCESS);
                } catch (Exception ex) {
                    if (isDebited) {
                        try {
                            senderStrategy.rollbackDebit(sender, tx.getAmount());
                        } catch (Exception rollbackEx) {
                            // Rollback logged/handled
                        }
                        tx.setState(TransactionState.ROLLED_BACK);
                    } else {
                        tx.setState(TransactionState.FAILED);
                    }
                    throw new RuntimeException("Transaction Failed: " + ex.getMessage(), ex);
                } finally {
                    transactionRepository.save(tx);
                }

                return tx;
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }

    public int getBalance(UUID accountId) {
        return accountRepository.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId))
                .getBalance();
    }

    public List<Transaction> getUserHistoryByUserId(UUID userId) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return transactionRepository.findByAccountIds(user.getAccounts().keySet());
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}

