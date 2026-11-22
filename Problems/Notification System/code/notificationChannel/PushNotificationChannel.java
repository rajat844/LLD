package code.notificationChannel;

import code.entities.Notification;
import code.entities.User;

public class PushNotificationChannel implements NotificationChannel {

    @Override
    public boolean send(User user, Notification notification) {
        String target = (user != null && user.getUserId() != null) ? user.getUserId().toString() : "unknown";
        System.out.println("[PushNotificationChannel] Notification " + notification.getId() + " sent via push to user " + target);
        return true;
    }
}

