package code.strategies;

import code.bookingState.Trip;

public interface CancellationStrategy {
    double getCancelCharges(Trip trip);
}

