package code.entities;

public class RetryPolicy {
    private int maxRetries;

    public RetryPolicy() {
        this(3);
    }

    public RetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public boolean shouldRetry() {
        if (maxRetries > 0) {
            maxRetries -= 1;
            return true;
        }
        return false;
    }

    public int getMaxRetries() {
        return maxRetries;
    }
}
