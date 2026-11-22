package code.strategies;

import code.entities.Location;
import code.entities.VehicleType;

public interface FareEstimationStrategy {
    double getFare(Location pickup, Location dropoff, VehicleType vehicleType);
}

