package entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class User {
    private final UUID userId;
    private final String name;
    private final Set<UUID> meetingIds = new HashSet<>();

    public User(UUID userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    void addMeeting(UUID meetingId) {
        meetingIds.add(meetingId);
    }

    public Set<UUID> getMeetingIds() {
        return Set.copyOf(meetingIds);
    }
}