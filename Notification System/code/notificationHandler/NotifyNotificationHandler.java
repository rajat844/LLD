package code.notificationHandler;

import code.entities.Notification;
import code.entities.NotificationDispatcher;
import code.entities.NotificationLevel;
import code.entities.User;

public class NotifyNotificationHandler extends NotificationHandler {
    public NotifyNotificationHandler() {
        super(NotificationLevel.NOTIFY);
    }

    public NotifyNotificationHandler(NotificationDispatcher dispatcher) {
        super(NotificationLevel.NOTIFY, dispatcher);
    }

    @Override
    protected void process(Notification notification, User user) {
        System.out.println("[NOTIFY] Processing notify notification: " + notification.getTitle());
        super.process(notification, user);
    }
}
