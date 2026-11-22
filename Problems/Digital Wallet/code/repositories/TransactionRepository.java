package code.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import code.entities.Transaction;

public class TransactionRepository {
    private final ConcurrentMap<UUID, Transaction> transactions = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, UUID> transactionIdsByIdempotencyKey = new ConcurrentHashMap<>();

    public synchronized Transaction save(Transaction transaction) {
        Objects.requireNonNull(transaction, "transaction");
        UUID existingId = transactionIdsByIdempotencyKey.get(transaction.getIdempotencyKey());
        if (existingId != null) {
            Transaction existing = transactions.get(existingId);
            if (!sameRequest(existing, transaction)) {
                throw new IllegalArgumentException("Idempotency key is already used by a different request");
            }
            if (existingId.equals(transaction.getId())) {
                transactions.put(transaction.getId(), transaction);
                return transaction;
            }
            return existing;
        }
        if (transactions.containsKey(transaction.getId())) {
            throw new IllegalArgumentException("Transaction already exists: " + transaction.getId());
        }
        transactions.put(transaction.getId(), transaction);
        transactionIdsByIdempotencyKey.put(transaction.getIdempotencyKey(), transaction.getId());
        return transaction;
    }

    public List<Transaction> findByAccountIds(Collection<UUID> accountIds) {
        if (accountIds == null || accountIds.isEmpty()) {
            return List.of();
        }
        List<Transaction> matches = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (accountIds.contains(transaction.getSenderAccountId())
                    || accountIds.contains(transaction.getReceiverAccountId())) {
                matches.add(transaction);
            }
        }
        matches.sort(Comparator.comparing(Transaction::getCreatedAt));
        return List.copyOf(matches);
    }

    public Optional<Transaction> findByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null) {
            return Optional.empty();
        }
        UUID transactionId = transactionIdsByIdempotencyKey.get(idempotencyKey);
        return transactionId == null ? Optional.empty() : Optional.ofNullable(transactions.get(transactionId));
    }

    public Optional<Transaction> findById(UUID transactionId) {
        if (transactionId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(transactions.get(transactionId));
    }

    public List<Transaction> findAll() {
        List<Transaction> all = new ArrayList<>(transactions.values());
        all.sort(Comparator.comparing(Transaction::getCreatedAt));
        return List.copyOf(all);
    }

    private boolean sameRequest(Transaction left, Transaction right) {
        return left.getSenderAccountId().equals(right.getSenderAccountId())
                && left.getReceiverAccountId().equals(right.getReceiverAccountId())
                && left.getAmount() == right.getAmount();
    }
}
