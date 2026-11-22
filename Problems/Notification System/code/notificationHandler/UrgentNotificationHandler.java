package code.notificationHandler;

import code.entities.Notification;
import code.entities.NotificationDispatcher;
import code.entities.NotificationLevel;
import code.entities.User;

public class UrgentNotificationHandler extends NotificationHandler {
    public UrgentNotificationHandler() {
        super(NotificationLevel.URGENT);
    }

    public UrgentNotificationHandler(NotificationDispatcher dispatcher) {
        super(NotificationLevel.URGENT, dispatcher);
    }

    @Override
    protected void process(Notification notification, User user) {
        System.out.println("[URGENT] Processing urgent notification: " + notification.getTitle());
        super.process(notification, user);
    }
}
