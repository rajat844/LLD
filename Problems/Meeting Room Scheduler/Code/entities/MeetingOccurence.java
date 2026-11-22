package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeetingOccurence {
    private UUID occurenceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID roomId;
    private boolean cancelled;

    public MeetingOccurence(LocalDateTime starTime, LocalDateTime endTime) {
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        this.occurenceId = UUID.randomUUID();
        this.startTime = starTime;
        this.endTime = endTime;
    }

    public void assignRoom(UUID roomId) {
        this.roomId = roomId;
    }

    public void cancel() {
        cancelled = true;
    }

    public UUID getOccurenceId() {
        return occurenceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
