package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeetingRoom {
    private UUID id;
    private final MeetingRoomSize size;
    private final MeetingRoomCalender calender;

    public MeetingRoom(UUID id, MeetingRoomSize size) {
        this.id = id;
        this.size = size;
        this.calender = new MeetingRoomCalender();
    }

    public UUID getId() {
        return id;
    }

    public MeetingRoomSize getSize() {
        return size;
    }

    public boolean isAvailable(LocalDateTime starTime, LocalDateTime endTime) {
        return calender.isAvailable(starTime, endTime);
    }

    public synchronized boolean book(MeetingOccurence occurence) {
        if (!isAvailable(occurence.getStartTime(), occurence.getEndTime()))
            return false;

        if (!calender.addSlot(occurence))
            return false;

        occurence.assignRoom(id);
        return true;
    }

    public void cancel(UUID occurenceId) {
        calender.removeSlot(occurenceId);
    }
}
