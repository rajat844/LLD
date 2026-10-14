package code.notificationHandler;

import code.entities.Notification;
import code.entities.NotificationDispatcher;
import code.entities.NotificationLevel;
import code.entities.User;

public class UpdateNotificationHandler extends NotificationHandler {
    public UpdateNotificationHandler() {
        super(NotificationLevel.UPDATE);
    }

    public UpdateNotificationHandler(NotificationDispatcher dispatcher) {
        super(NotificationLevel.UPDATE, dispatcher);
    }

    @Override
    protected void process(Notification notification, User user) {
        System.out.println("[UPDATE] Processing update notification: " + notification.getTitle());
        super.process(notification, user);
    }
}
