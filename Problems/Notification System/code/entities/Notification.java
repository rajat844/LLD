package code.entities;

import java.util.UUID;

public class Notification {
    private UUID notificationId;
    private UUID userId;
    private String title;
    private String description;
    private NotificationStatus status;
    private ChannelType channel;
    private NotificationLevel level;

    public Notification(UUID userId, String title, String description, ChannelType channel, NotificationLevel level) {
        this.notificationId = UUID.randomUUID();
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.channel = channel;
        this.status = NotificationStatus.PENDING;
        this.level = level;
    }

    public void updateStatus(NotificationStatus status) {
        this.status = status;
    }

    public NotificationLevel getLevel() {
        return this.level;
    }

    public UUID getNotificationId() {
        return notificationId;
    }
    
    public UUID getId() {
        return notificationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public ChannelType getChannel() {
        return channel;
    }

    public void print() {
        System.out.println(this.title + " " + this.description);
    }
}
