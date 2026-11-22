package code.notificationChannel;

import java.util.HashMap;
import java.util.Map;
import code.entities.ChannelType;

public class ChannelFactory {
    private Map<ChannelType, NotificationChannel> channelMap;

    public ChannelFactory() {
        this.channelMap = new HashMap<>();
        this.channelMap.put(ChannelType.EMAIL, new EmailChannel());
        this.channelMap.put(ChannelType.SMS, new SmsChannel());
        this.channelMap.put(ChannelType.PUSH, new PushNotificationChannel());
    }

    public ChannelFactory(Map<ChannelType, NotificationChannel> channelMap) {
        this.channelMap = channelMap;
    }

    public NotificationChannel getChannel(ChannelType channelType) {
        if (channelMap == null || channelType == null) {
            return null;
        }
        return channelMap.get(channelType);
    }
}

