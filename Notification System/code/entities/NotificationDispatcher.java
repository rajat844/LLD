package code.entities;

import java.util.List;
import code.notificationChannel.ChannelFactory;
import code.notificationChannel.NotificationChannel;
import code.repositories.NotificationRepository;
import code.repositories.NotificationRepository;

public class NotificationDispatcher {
    private RetryPolicy retryPolicy;
    private ChannelFactory channelFactory;
    private NotificationRepository notificationRepository;

    public NotificationDispatcher(RetryPolicy retryPolicy, ChannelFactory channelFactory,
            NotificationRepository notificationRepository) {
        this.retryPolicy = retryPolicy;
        this.channelFactory = channelFactory;
        this.notificationRepository = notificationRepository;
    }

    public boolean dispatch(Notification notification, User user) {
        List<ChannelType> subscribed = user.getSubscribedChannels();
        if (subscribed != null && !subscribed.isEmpty() && !subscribed.contains(notification.getChannel())) {
            System.out.println(
                    "User " + user.getUserId() + " is not subscribed to channel: " + notification.getChannel());
            notification.updateStatus(NotificationStatus.FAILED);
            if (notificationRepository != null) {
                notificationRepository.updateStatus(notification.getId(), NotificationStatus.FAILED);
            }
            return false;
        }

        NotificationChannel channel = channelFactory.getChannel(notification.getChannel());

        boolean sent = channel.send(user, notification);
        while (!sent && retryPolicy.shouldRetry()) {
            System.out.println("Retrying sending notification " + notification.getId() + "...");
            sent = channel.send(user, notification);
        }

        NotificationStatus finalStatus = sent ? NotificationStatus.SENT
                : NotificationStatus.FAILED;
        notification.updateStatus(finalStatus);
        notificationRepository.updateStatus(notification.getId(), finalStatus);

        return sent;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public ChannelFactory getChannelFactory() {
        return channelFactory;
    }

    public void setChannelFactory(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
