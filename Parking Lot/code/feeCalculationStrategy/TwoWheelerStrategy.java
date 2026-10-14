package code.feeCalculationStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import code.entities.Bill;
import code.entities.Ticket;
import code.entities.VehicleType;

public class TwoWheelerStrategy implements FeeCalculationStrategy {
    private static int TWO_WHEELER_CHARGES;

    public TwoWheelerStrategy(int charges) {
        TWO_WHEELER_CHARGES = charges;
    }

    public Bill calculate(VehicleType type, Ticket ticket) {
        long time = ChronoUnit.MINUTES.between(ticket.getEntryTime(), LocalDateTime.now());
        int amount = TWO_WHEELER_CHARGES * (int) time;

        return new Bill(amount);
    }
}