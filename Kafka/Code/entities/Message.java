package Code.entities;

import java.time.LocalDateTime;

public class Message {
    private String key;
    private String message;
    private LocalDateTime timestamp;
    private int offset;

    public Message(String key, String message) {
        this.key = key;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.offset = -1;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int offset(int offset) {
        return offset;
    }
}
