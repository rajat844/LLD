package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import entities.MeetingRoomSize;
import entities.MeetingRoom;

public class MeetingRoomRepository {
    private Map<UUID, MeetingRoom> rooms;

    public MeetingRoomRepository() {
        this.rooms = new HashMap<>();
    }

    public void addMeetingRoom(MeetingRoom room) {
        rooms.put(room.getId(), room);
    }

    public void removeMeetingRoom(MeetingRoom room) {
        if (rooms.containsKey(room.getId())) {
            rooms.remove(room.getId());
        }
    }

    public Optional<MeetingRoom> findById(UUID roomId) {
        return Optional.ofNullable(rooms.get(roomId));
    }

    public ArrayList<MeetingRoom> getAllMeetingRooms(MeetingRoomSize size) {
        ArrayList<MeetingRoom> res = new ArrayList<>();
        for (UUID id : rooms.keySet()) {
            if (rooms.get(id).getSize().equals(size)) {
                res.add(rooms.get(id));
            }
        }

        return res;
    }

}
