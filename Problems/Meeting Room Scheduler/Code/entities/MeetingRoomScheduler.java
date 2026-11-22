package entities;

import repositories.MeetingRepository;
import repositories.MeetingRoomRepository;
import repositories.UserRepository;
import roomSelectionStrategy.RoomSelectionStrategy;

public class MeetingRoomScheduler {
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final RoomSelectionStrategy strategy;

}
