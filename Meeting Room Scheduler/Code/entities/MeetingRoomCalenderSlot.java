package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeetingRoomCalenderSlot {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public MeetingRoomCalenderSlot(UUID id, LocalDateTime starTime, LocalDateTime endTime) {
        this.id = id;
        this.startTime = starTime;
        this.endTime = endTime;
    }

    public UUID getId() {
        return id;
    }

    public boolean overlaps(LocalDateTime requestedStart, LocalDateTime requestedEnd) {
        return startTime.isBefore(requestedStart) && endTime.isAfter(requestedEnd);
    }
}
