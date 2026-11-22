package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MeetingRoomCalender {
    private final List<MeetingRoomCalenderSlot> slots;

    public MeetingRoomCalender() {
        slots = new ArrayList<>();
    }

    public synchronized boolean isAvailable(LocalDateTime starTime, LocalDateTime endTime) {
        return slots.stream().noneMatch(slot -> slot.overlaps(starTime, endTime));
    }

    public synchronized boolean addSlot(MeetingOccurence occurence) {
        if (!isAvailable(occurence.getStartTime(), occurence.getEndTime())) {
            return false;
        }
        slots.add(new MeetingRoomCalenderSlot(occurence.getOccurenceId(), occurence.getStartTime(),
                occurence.getEndTime()));

        return true;
    }

    public synchronized void removeSlot(UUID occurenceId) {
        slots.removeIf(slot -> slot.getId().equals(occurenceId));
    }
}
