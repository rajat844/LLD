package repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import entities.Meeting;
import entities.MeetingOccurence;

public final class MeetingRepository {
    private final Map<UUID, Meeting> meetings = new HashMap<>();

    public void save(Meeting meeting) {
        meetings.put(meeting.getId(), meeting);
    }

    public Optional<Meeting> findById(UUID meetingId) {
        return Optional.ofNullable(meetings.get(meetingId));
    }

    public Optional<MeetingOccurence> findOccurrence(UUID occurrenceId) {
        return meetings.values().stream()
                .map(meeting -> meeting.findOccurence(occurrenceId))
                .flatMap(Optional::stream)
                .findFirst();
    }
}