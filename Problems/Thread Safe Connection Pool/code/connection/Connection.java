package code.connection;

public interface Connection {
    void execute(String query);
    void close();
    boolean isValid();
}

