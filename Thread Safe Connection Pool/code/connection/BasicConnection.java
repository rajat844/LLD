package code.connection;

public class BasicConnection implements Connection {
    private String socketUrl;
    private boolean isOpen;

    public BasicConnection(String socketUrl) {
        this.socketUrl = socketUrl;
        this.isOpen = true;
    }

    @Override
    public void execute(String query) {
        // Execute the command
    }

    @Override
    public void close() {
        this.socketUrl = null;
        this.isOpen = false;
    }

    @Override
    public boolean isValid() {
        return isOpen;
    }

    public String getSocketUrl() {
        return socketUrl;
    }

    public boolean isOpen() {
        return isOpen;
    }
}

