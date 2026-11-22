package code.entities;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private List<ParkingFloor> floors;

    Parking(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<ParkingFloor> getFloors() {
        return new ArrayList<>(floors);
    }
}
