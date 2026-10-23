package code.decorator;

import code.connection.Connection;

public abstract class ConnectionDecorator implements Connection {
    protected Connection wrappedConnection;

    public ConnectionDecorator(Connection wrappedConnection) {
        this.wrappedConnection = wrappedConnection;
    }

    @Override
    public void execute(String query) {
        if (wrappedConnection != null) {
            wrappedConnection.execute(query);
        }
    }

    @Override
    public void close() {
        if (wrappedConnection != null) {
            wrappedConnection.close();
        }
    }

    @Override
    public boolean isValid() {
        return wrappedConnection != null && wrappedConnection.isValid();
    }

    public Connection getWrappedConnection() {
        return wrappedConnection;
    }
}

