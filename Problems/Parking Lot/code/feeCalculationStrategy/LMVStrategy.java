package code.feeCalculationStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import code.entities.Bill;
import code.entities.Ticket;
import code.entities.VehicleType;

public class LMVStrategy implements FeeCalculationStrategy {
    private static int LMV_CHARGES;

    public LMVStrategy(int charges) {
        LMV_CHARGES = charges;
    }

    public Bill calculate(VehicleType type, Ticket ticket) {
        long time = ChronoUnit.MINUTES.between(ticket.getEntryTime(), LocalDateTime.now());
        int amount = LMV_CHARGES * (int) time;

        return new Bill(amount);
    }
}