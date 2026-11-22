package code.notificationChannel;

import code.entities.Notification;
import code.entities.User;

public class SmsChannel implements NotificationChannel {

    @Override
    public boolean send(User user, Notification notification) {
        String phone = (user != null && user.getPhoneNumber() != null) ? user.getPhoneNumber() : "unknown";
        System.out.println("[SmsChannel] Notification " + notification.getId() + " sent via SMS to " + phone);
        return true;
    }
}