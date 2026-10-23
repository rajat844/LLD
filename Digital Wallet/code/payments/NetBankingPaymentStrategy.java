package code.payments;

import code.accounts.Account;
import code.accounts.AccountType;

public class NetBankingPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean authorizeAndDebit(Account source, int amount) {
        if (source == null) {
            throw new IllegalArgumentException("Source account cannot be null");
        }
        if (source.getType() != AccountType.NET_BANKING) {
            throw new IllegalArgumentException("NetBankingPaymentStrategy only supports NetBanking accounts");
        }
        if (!source.validate()) {
            throw new IllegalArgumentException("NetBanking authorization failed: invalid netbanking credentials");
        }
        if (!source.debit(amount)) {
            throw new IllegalStateException("Failed to debit amount from NetBanking account. Insufficient balance or invalid amount: " + amount);
        }
        return true;
    }
}
