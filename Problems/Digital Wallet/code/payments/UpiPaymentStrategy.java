package code.payments;

import code.accounts.Account;
import code.accounts.AccountType;

public class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean authorizeAndDebit(Account source, int amount) {
        if (source == null) {
            throw new IllegalArgumentException("Source account cannot be null");
        }
        if (source.getType() != AccountType.UPI) {
            throw new IllegalArgumentException("UpiPaymentStrategy only supports UPI accounts");
        }
        if (!source.validate()) {
            throw new IllegalArgumentException("UPI authorization failed: invalid UPI ID or PIN");
        }
        if (!source.debit(amount)) {
            throw new IllegalStateException("Failed to debit amount from UPI account. Insufficient balance or invalid amount: " + amount);
        }
        return true;
    }
}
