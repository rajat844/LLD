package code.accounts;

import java.util.UUID;

public class UpiAccount extends Account {
    private final String upiId;
    private final String pin;

    public UpiAccount(UUID id, UUID userId, String upiId, String pin, int balance) {
        super(id, userId, AccountType.UPI, balance);
        this.upiId = upiId;
        this.pin = pin;
    }

    public String getUpiId() {
        return upiId;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean validate() {
        return upiId != null && upiId.matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+")
                && pin != null && pin.matches("\\d{4,6}");
    }
}
