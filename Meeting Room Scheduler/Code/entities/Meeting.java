package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Meeting {
    private List<MeetingOccurence> occurences;
    private UUID id;
    private UUID ownerId;
    private int particitpantCount;
    private MeetingType type;

    Meeting(UUID ownerId, int participationCount, MeetingType type) {
        this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.occurences = new ArrayList<>();
        this.type = type;
    }

    public List<MeetingOccurence> getOccurence() {
        return new ArrayList<>(occurences);
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public int getParticitpantCount() {
        return particitpantCount;
    }

    public MeetingType getType() {
        return type;
    }

    public void addOccurence(MeetingOccurence occurence) {
        occurences.add(occurence);
    }

    public Optional<MeetingOccurence> findOccurence(UUID occurenceId) {
        return occurences.stream().filter(occurence -> occurence.getOccurenceId().equals(occurenceId)).findFirst();
    }

}
