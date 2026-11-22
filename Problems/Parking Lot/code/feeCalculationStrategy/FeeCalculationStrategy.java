package code.feeCalculationStrategy;

import code.entities.Bill;
import code.entities.VehicleType;
import code.entities.Ticket;

public interface FeeCalculationStrategy {
    public Bill calculate(VehicleType type, Ticket ticket);
}