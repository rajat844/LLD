package code.accounts;

import java.time.YearMonth;
import java.util.UUID;

public class CardAccount extends Account {
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;

    public CardAccount(UUID id, UUID userId, AccountType type, String cardNumber, String cvv, String expiryDate, int balance) {
        super(id, userId, type, balance);
        if (type != AccountType.CREDIT_CARD && type != AccountType.DEBIT_CARD) {
            throw new IllegalArgumentException("Card accounts must use a card account type");
        }
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean validate() {
        if (cardNumber == null || !cardNumber.matches("\\d{12,19}")
                || cvv == null || !cvv.matches("\\d{3,4}") || expiryDate == null) {
            return false;
        }
        try {
            YearMonth ym;
            if (expiryDate.contains("/")) {
                String[] parts = expiryDate.split("/");
                if (parts.length == 2) {
                    int month = Integer.parseInt(parts[0].trim());
                    int year = Integer.parseInt(parts[1].trim());
                    if (year < 100) {
                        year += 2000;
                    }
                    ym = YearMonth.of(year, month);
                } else {
                    return false;
                }
            } else {
                ym = YearMonth.parse(expiryDate);
            }
            return !ym.isBefore(YearMonth.now());
        } catch (Exception exception) {
            return false;
        }
    }
}
