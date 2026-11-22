package roomSelectionStrategy;

import java.util.List;

import entities.MeetingRoom;

public class AssignMeetingRoomBySize implements RoomSelectionStrategy {
    @Override
    public MeetingRoom assignMeetingRoom(List<MeetingRoom> rooms, int participantCount) {
        return rooms.stream()
                .filter(room -> room.getSize().getCapacity() >= participantCount)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No suitable room is available"));
    }
}
