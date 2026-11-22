package code.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import code.feeCalculationStrategy.FeeCalculationStrategy;
import code.feeCalculationStrategy.HMVStrategy;
import code.feeCalculationStrategy.LMVStrategy;
import code.feeCalculationStrategy.TwoWheelerStrategy;
import code.parkingStrategy.NearestParkingStrategy;
import code.parkingStrategy.ParkingStrategy;

public class ParkingManager {
    private Parking parking;
    private HashMap<VehicleType, FeeCalculationStrategy> calculationStrategies;
    private ParkingStrategy strategy;

    ParkingManager(Parking parking) {
        this.parking = parking;
        this.strategy = new NearestParkingStrategy();
        this.calculationStrategies = new HashMap<>();
        calculationStrategies.put(VehicleType.TWO_WHEELER, new TwoWheelerStrategy(50));
        calculationStrategies.put(VehicleType.LIGHT_MOTOR_VEHICLE, new LMVStrategy(100));
        calculationStrategies.put(VehicleType.HEAVY_MOTOR_VEHICLE, new HMVStrategy(150));
    }

    public Ticket onEntry(String registrationNumber, VehicleType type) {
        List<ParkingSlot> slots = parking.getFloors().stream()
                .flatMap(floor -> floor.getSlots().stream())
                .collect(Collectors.toList());
        Optional<ParkingSlot> slot = strategy.geSlot(type, slots);

        if (!slot.isPresent()) {
            throw new IllegalStateException("No available parking slot for vehicle type " + type);
        }

        ParkingSlot allocatedSlot = slot.get();
        allocatedSlot.setFree(false);

        Vehicle vehicle = new Vehicle(registrationNumber, type);
        return new Ticket(vehicle, allocatedSlot);
    }

    public Bill onExit(Ticket ticket) {
        VehicleType type = ticket.getVehicle().getType();
        FeeCalculationStrategy calculationStrategy = calculationStrategies.get(type);
        if (calculationStrategy == null) {
            throw new IllegalStateException("No fee calculation strategy for vehicle type " + type);
        }

        Bill bill = calculationStrategy.calculate(type, ticket);
        ticket.getSlot().setFree(true);
        return bill;
    }

}
