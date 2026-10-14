package code.notificationChannel;

import code.entities.Notification;
import code.entities.User;

public class EmailChannel implements NotificationChannel {

    @Override
    public boolean send(User user, Notification notification) {
        String recipient = (user != null && user.getEmailId() != null) ? user.getEmailId() : "unknown";
        System.out.println("[EmailChannel] Notification " + notification.getId() + " sent via email to " + recipient);
        return true;
    }
}
