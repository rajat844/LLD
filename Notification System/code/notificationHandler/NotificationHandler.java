package code.notificationHandler;

import code.entities.Notification;
import code.entities.NotificationDispatcher;
import code.entities.NotificationLevel;
import code.entities.User;

public abstract class NotificationHandler {
    private NotificationLevel level;
    private NotificationHandler nextHandler;
    private NotificationDispatcher dispatcher;

    public NotificationHandler(NotificationLevel level) {
        this.level = level;
    }

    public NotificationHandler(NotificationLevel level, NotificationDispatcher dispatcher) {
        this.level = level;
        this.dispatcher = dispatcher;
    }

    public NotificationHandler setNext(NotificationHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public NotificationHandler getNextHandler() {
        return nextHandler;
    }

    public NotificationDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public NotificationLevel getLevel() {
        return level;
    }

    public void handle(Notification notification, User user) {
        if (notification == null) {
            return;
        }

        if (canHandle(notification)) {
            process(notification, user);
        } else if (nextHandler != null) {
            nextHandler.handle(notification, user);
        } else {
            System.out.println("No handler found in chain for notification level: " + notification.getLevel());
        }
    }

    public void handle(Notification notification) {
        handle(notification, null);
    }

    protected boolean canHandle(Notification notification) {
        return notification != null && notification.getLevel() == this.level;
    }

    protected void process(Notification notification, User user) {
        if (dispatcher != null && user != null) {
            dispatcher.dispatch(notification, user);
        } else {
            notification.print();
        }
    }
}
