package code.entities;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import code.connection.Connection;
import code.factory.ConnectionFactory;

public class ConnectionPool {
    private static ConnectionPool instance;

    private BlockingQueue<Connection> idleQueue;
    private int maxPoolSize;
    private AtomicInteger currentSize;
    private ConnectionFactory factory;
    private int minIdle;

    private ConnectionPool(ConnectionFactory factory, int maxPoolSize) {
        this.factory = factory;
        this.maxPoolSize = maxPoolSize;
        this.minIdle = maxPoolSize / 2;
        this.idleQueue = new LinkedBlockingQueue<>(maxPoolSize);

        for (int i = 0; i < minIdle; i++) {
            idleQueue.offer(factory.createConnection());
            currentSize.incrementAndGet();
        }
    }

    public static synchronized ConnectionPool getInstance(ConnectionFactory factory, int maxPoolSize) {
        if (instance == null) {
            instance = new ConnectionPool(factory, maxPoolSize);
        }
        return instance;
    }

    public static synchronized ConnectionPool getInstance() {
        return instance;
    }

    public Connection acquire() throws TimeoutException {
        Connection conn = idleQueue.poll();
        if (conn == null) {
            if (currentSize.get() < maxPoolSize
                    && currentSize.compareAndSet(currentSize.get(), currentSize.get() + 1)) {
                conn = factory.createConnection();
            } else {
                throw new TimeoutException("Timed out waiting for a connection");
            }
        }
        if (!conn.isValid()) {
            conn = factory.createConnection();
        }

        return conn;
    }

    public void release(Connection connection) {
        idleQueue.offer(connection);
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public int getCurrentSize() {
        return currentSize != null ? currentSize.get() : 0;
    }

    public ConnectionFactory getFactory() {
        return factory;
    }
}
