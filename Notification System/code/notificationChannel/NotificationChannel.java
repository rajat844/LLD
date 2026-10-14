package code.notificationChannel;

import code.entities.Notification;
import code.entities.User;

public interface NotificationChannel {
    public boolean send(User user, Notification notification);
}
