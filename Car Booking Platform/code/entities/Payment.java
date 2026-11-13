package code.entities;

import java.util.UUID;

public class Payment {
    private double amount;
    private UUID transactionId;
    private PaymentStatus paymentStatus;

    public Payment(double amount) {
        this.amount = amount;
        this.transactionId = UUID.randomUUID();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
