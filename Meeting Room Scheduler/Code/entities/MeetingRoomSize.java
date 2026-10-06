package entities;

public enum MeetingRoomSize {
    SMALL(4),
    MEDIUM(8),
    LARGE(20);

    private final int capacity;

    MeetingRoomSize(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
