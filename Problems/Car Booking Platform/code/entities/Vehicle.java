package code.entities;

public class Vehicle {
    private String id;
    private VehicleType type;
    private String model;

    public Vehicle(String id, VehicleType type, String model) {
        this.id = id;
        this.type = type;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return type;
    }

    public String getModel() {
        return model;
    }
}
