package code.entities;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Transaction {
    private final UUID id;
    private final UUID senderAccountId;
    private final UUID receiverAccountId;
    private final int amount;
    private TransactionState state;
    private final Instant createdAt;
    private final String idempotencyKey;

    public Transaction(UUID id, UUID senderAccountId, UUID receiverAccountId, int amount,
            TransactionState state, Instant createdAt, String idempotencyKey) {
        this.id = Objects.requireNonNull(id, "id");
        this.senderAccountId = Objects.requireNonNull(senderAccountId, "senderAccountId");
        this.receiverAccountId = Objects.requireNonNull(receiverAccountId, "receiverAccountId");
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        this.amount = amount;
        this.state = Objects.requireNonNull(state, "state");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt");
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("idempotencyKey must not be blank");
        }
        this.idempotencyKey = idempotencyKey;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSenderAccountId() {
        return senderAccountId;
    }

    public UUID getReceiverAccountId() {
        return receiverAccountId;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionState getState() {
        return state;
    }

    public void setState(TransactionState state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public Transaction withState(TransactionState newState) {
        return new Transaction(id, senderAccountId, receiverAccountId, amount,
                newState, createdAt, idempotencyKey);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderAccountId=" + senderAccountId +
                ", receiverAccountId=" + receiverAccountId +
                ", amount=" + amount +
                ", state=" + state +
                ", createdAt=" + createdAt +
                ", idempotencyKey='" + idempotencyKey + '\'' +
                '}';
    }
}
