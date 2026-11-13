package code.entities;

import java.util.UUID;

public class Payment {
    private double amount;
    private UUID transactionId;
    private PaymentStatus paymentStatus;

    public Payment(double amount, UUID transactionId, PaymentStatus paymentStatus) {
        this.amount = amount;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
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

