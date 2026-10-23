package code.entities;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import code.accounts.Account;

public class User {
    private final UUID userId;
    private final Map<UUID, Account> accounts = new LinkedHashMap<>();
    private UUID defaultAccountId;

    public User(UUID userId) {
        this.userId = Objects.requireNonNull(userId, "userId");
    }

    public UUID getUserId() {
        return userId;
    }

    public synchronized Optional<Account> getAccount(UUID accountId) {
        return Optional.ofNullable(accounts.get(Objects.requireNonNull(accountId, "accountId")));
    }

    public synchronized void addAccount(Account account) {
        Objects.requireNonNull(account, "account");
        if (!userId.equals(account.getUserId())) {
            throw new IllegalArgumentException("Account belongs to a different user");
        }
        if (accounts.putIfAbsent(account.getId(), account) != null) {
            throw new IllegalArgumentException("Account already exists for this user");
        }
        if (defaultAccountId == null) {
            defaultAccountId = account.getId();
        }
    }

    public synchronized void setDefaultAccount(UUID accountId) {
        if (!accounts.containsKey(Objects.requireNonNull(accountId, "accountId"))) {
            throw new IllegalArgumentException("Default account must belong to this user");
        }
        defaultAccountId = accountId;
    }

    public synchronized Optional<Account> getDefaultAccount() {
        return Optional.ofNullable(defaultAccountId).map(accounts::get);
    }

    public synchronized UUID getDefaultAccountId() {
        return defaultAccountId;
    }

    public synchronized Map<UUID, Account> getAccounts() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(accounts));
    }
}
