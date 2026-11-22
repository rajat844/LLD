package roomSelectionStrategy;

import java.util.List;

import entities.MeetingRoom;

public interface RoomSelectionStrategy {
    MeetingRoom assignMeetingRoom(List<MeetingRoom> rooms, int participantCount);
}
