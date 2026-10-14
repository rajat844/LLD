package code.feeCalculationStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import code.entities.Bill;
import code.entities.Ticket;
import code.entities.VehicleType;

public class HMVStrategy implements FeeCalculationStrategy {
    private static int HMV_CHARGES;

    public HMVStrategy(int charges) {
        HMV_CHARGES = charges;
    }

    public Bill calculate(VehicleType type, Ticket ticket) {
        long time = ChronoUnit.MINUTES.between(ticket.getEntryTime(), LocalDateTime.now());
        int amount = HMV_CHARGES * (int) time;

        return new Bill(amount);
    }
}
