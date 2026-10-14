package code.parkingStrategy;

import java.util.List;
import java.util.Optional;

import code.entities.ParkingSlot;
import code.entities.VehicleType;

public class NearestParkingStrategy implements ParkingStrategy {

    @Override
    public Optional<ParkingSlot> geSlot(VehicleType type, List<ParkingSlot> slots) {
        for (ParkingSlot slot : slots) {
            if (slot.isFree() && slot.getType() == type)
                return Optional.of(slot);
        }

        return Optional.empty();
    }
}
