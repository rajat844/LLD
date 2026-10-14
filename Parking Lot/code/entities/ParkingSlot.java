package code.entities;

import java.util.UUID;

public class ParkingSlot {
    private UUID id;
    private VehicleType type;
    private boolean isFree;
    
    public ParkingSlot(VehicleType type){
        this.id = UUID.randomUUID();
        this.type = type;
        this.isFree = true;
    }

    public UUID getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }    
}
