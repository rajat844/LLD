package code.repositories;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import code.accounts.Account;

public class AccountRepository {
    private final ConcurrentMap<UUID, Account> accounts = new ConcurrentHashMap<>();

    public void addAccount(Account account) {
        Objects.requireNonNull(account, "account");
        if (accounts.putIfAbsent(account.getId(), account) != null) {
            throw new IllegalArgumentException("Account already exists: " + account.getId());
        }
    }

    public Optional<Account> getAccountById(UUID accountId) {
        if (accountId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(accounts.get(accountId));
    }
}
