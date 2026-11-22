package code.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import code.entities.Notification;
import code.entities.NotificationStatus;

public class NotificationRepository {
    private final Map<UUID, Notification> notifications;

    public NotificationRepository() {
        this.notifications = new HashMap<>();
    }

    public void save(Notification notification) {
        if (notification != null) {
            notifications.put(notification.getId(), notification);
        }
    }

    public Notification getById(UUID id) {
        return notifications.get(id);
    }

    public void updateStatus(UUID id, NotificationStatus status) {
        Notification notification = getById(id);
        if (notification != null) {
            notification.updateStatus(status);
        }
    }
}
