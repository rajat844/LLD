package code.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID userId;
    private Set<ChannelType> subscribedChannels;
    private String emailId;
    private String phoneNumber;

    public User(UUID userId) {
        this.userId = userId;
        this.subscribedChannels = new HashSet<>();
    }

    public UUID getUserId() {
        return userId;
    }

    public List<ChannelType> getSubscribedChannels() {
        return new ArrayList<>(subscribedChannels);
    }

    public void subscribe(ChannelType channel) {
        if (channel != null) {
            this.subscribedChannels.add(channel);
        }
    }

    public void addNotificationChannel(ChannelType channel) {
        subscribe(channel);
    }

    public boolean removeNotificationChannel(ChannelType channel) {
        return this.subscribedChannels.remove(channel);
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Alias for mobileNumber
    public String getMobileNumber() {
        return phoneNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.phoneNumber = mobileNumber;
    }
}
