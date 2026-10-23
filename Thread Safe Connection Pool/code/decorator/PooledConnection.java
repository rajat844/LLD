package code.decorator;

import code.connection.Connection;
import code.entities.ConnectionPool;

public class PooledConnection extends ConnectionDecorator {
    private ConnectionPool pool;
    private boolean isReleased;

    public PooledConnection(Connection wrappedConnection, ConnectionPool pool) {
        super(wrappedConnection);
        this.pool = pool;
        this.isReleased = false;
    }

    @Override
    public void execute(String query) {
        // execute the query
    }

    @Override
    public void close() {
        super.close();
        this.pool = null;
        this.isReleased = true;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public ConnectionPool getPool() {
        return pool;
    }
}
