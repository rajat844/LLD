package code.entities;

import java.util.UUID;
import code.notificationChannel.ChannelFactory;
import code.notificationHandler.NotificationHandler;
import code.notificationHandler.NotifyNotificationHandler;
import code.notificationHandler.UpdateNotificationHandler;
import code.notificationHandler.UrgentNotificationHandler;
import code.repositories.NotificationRepository;
import code.repositories.InMemoryUserRepository;
import code.repositories.NotificationRepository;
import code.repositories.UserRepository;

public class NotificationManager {
    private UserRepository userRepository;
    private NotificationRepository notificationRepository;
    private NotificationHandler notificationHandler;

    public NotificationManager(UserRepository userRepository,
            NotificationRepository notificationRepository,
            NotificationHandler notificationHandler) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.notificationHandler = notificationHandler;
    }

    public NotificationManager() {
        this.userRepository = new InMemoryUserRepository();
        this.notificationRepository = new NotificationRepository();

        NotificationDispatcher dispatcher = new NotificationDispatcher(
                new RetryPolicy(),
                new ChannelFactory(),
                this.notificationRepository);

        NotificationHandler urgentHandler = new UrgentNotificationHandler(dispatcher);
        NotificationHandler notifyHandler = new NotifyNotificationHandler(dispatcher);
        NotificationHandler updateHandler = new UpdateNotificationHandler(dispatcher);

        urgentHandler.setNext(notifyHandler);
        notifyHandler.setNext(updateHandler);

        this.notificationHandler = urgentHandler;
    }

    public void processNotification(Notification notification) {
        notificationRepository.save(notification);
        User user = userRepository.getUser(notification.getUserId());
        if (notificationHandler != null) {
            notificationHandler.handle(notification, user);
        } else {
            System.err.println("No notification handler configured in NotificationManager.");
        }
    }

    public Notification processNotification(UUID userId, String title, String description, ChannelType channel,
            NotificationLevel level) {
        Notification notification = new Notification(userId, title, description, channel, level);
        processNotification(notification);
        return notification;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationHandler getNotificationHandler() {
        return notificationHandler;
    }

    public void setNotificationHandler(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }
}
