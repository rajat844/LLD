package code.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, ParkingSlot slot) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }    
}