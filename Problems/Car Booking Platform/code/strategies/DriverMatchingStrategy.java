package code.strategies;

import java.util.List;

import code.entities.Driver;
import code.entities.Location;
import code.entities.VehicleType;

public interface DriverMatchingStrategy {
    Driver getDriver(Location pickup, VehicleType vehicleType, List<Driver> availableDrivers);
}

