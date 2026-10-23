package code.decorator;

import code.connection.Connection;

public class HealthCheckConnection extends ConnectionDecorator {
    private long createdAt;
    private long maxLifeTime;

    public HealthCheckConnection(Connection wrappedConnection, long maxLifeTime) {
        super(wrappedConnection);
        this.createdAt = System.currentTimeMillis();
        this.maxLifeTime = maxLifeTime;
    }

    @Override
    public boolean isValid() {
        if (System.currentTimeMillis() - this.createdAt > maxLifeTime)
            return false;
        return true;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getMaxLifeTime() {
        return maxLifeTime;
    }
}
