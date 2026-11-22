package code.entities;

import java.time.LocalDateTime;

public class LogMessage {
    private LogType type;
    private String message;
    private LocalDateTime timestamp;

    public LogMessage(LogType type, String message, LocalDateTime timestamp) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public LogType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
