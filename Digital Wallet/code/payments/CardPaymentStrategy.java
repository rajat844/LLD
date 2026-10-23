package code.payments;

import code.accounts.Account;
import code.accounts.AccountType;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean authorizeAndDebit(Account source, int amount) {
        if (source == null) {
            throw new IllegalArgumentException("Source account cannot be null");
        }
        if (source.getType() != AccountType.CREDIT_CARD && source.getType() != AccountType.DEBIT_CARD) {
            throw new IllegalArgumentException("CardPaymentStrategy only supports card accounts");
        }
        if (!source.validate()) {
            throw new IllegalArgumentException("Card authorization failed: invalid card details or expired card");
        }
        if (!source.debit(amount)) {
            throw new IllegalStateException("Failed to debit amount from card account. Insufficient balance or invalid amount: " + amount);
        }
        return true;
    }
}
