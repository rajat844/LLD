package code.accounts;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {
    private final UUID id;
    private final UUID userId;
    private int balance;
    private final AccountType type;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(UUID id, UUID userId, AccountType type, int balance) {
        this.id = Objects.requireNonNull(id, "id");
        this.userId = Objects.requireNonNull(userId, "userId");
        this.type = Objects.requireNonNull(type, "type");
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = balance;
    }

    public boolean debit(int amount) {
        lock.lock();
        try {
            if (amount <= 0 || amount > balance) {
                return false;
            }
            balance -= amount;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean credit(int amount) {
        lock.lock();
        try {
            if (amount <= 0) {
                return false;
            }
            balance = Math.addExact(balance, amount);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return this.lock;
    }

    public int getBalance() {
        lock.lock();
        try {
            return this.balance;
        } finally {
            lock.unlock();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public AccountType getType() {
        return type;
    }

    public abstract boolean validate();
}
