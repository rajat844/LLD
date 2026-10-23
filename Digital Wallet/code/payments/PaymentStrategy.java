package code.payments;

import code.accounts.Account;

public interface PaymentStrategy {
    boolean authorizeAndDebit(Account source, int amount);

    default boolean credit(Account destination, int amount) {
        if (destination == null || !destination.validate()) {
            throw new IllegalArgumentException("Destination account is invalid");
        }
        if (!destination.credit(amount)) {
            throw new IllegalStateException("Failed to credit amount to destination account: " + amount);
        }
        return true;
    }

    default boolean rollbackDebit(Account source, int amount) {
        if (source == null) {
            throw new IllegalArgumentException("Source account cannot be null");
        }
        if (!source.credit(amount)) {
            throw new IllegalStateException("Failed to rollback debit on source account: " + amount);
        }
        return true;
    }
}
