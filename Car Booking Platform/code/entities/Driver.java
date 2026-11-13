package code.entities;

import java.util.UUID;

public class Driver {
    private UUID id;
    private String name;
    private Vehicle vehicle;
    private DriverStatus status;
    private Location location;

    public Driver(UUID id, String name, Vehicle vehicle, Location location, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.vehicle = vehicle;
        this.location = location;
        this.status = status;
    }

    public void changeStatus(DriverStatus status) {
        this.status = status;
    }

}
