package code.parkingStrategy;

import java.util.List;
import java.util.Optional;

import code.entities.ParkingSlot;
import code.entities.VehicleType;

public interface ParkingStrategy {
    public Optional<ParkingSlot> geSlot(VehicleType type, List<ParkingSlot> slots);
}
