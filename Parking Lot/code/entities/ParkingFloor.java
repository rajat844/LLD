package code.entities;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private List<ParkingSlot> slots;

    ParkingFloor(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public List<ParkingSlot> getSlots() {
        return new ArrayList<>(slots);
    }
}
