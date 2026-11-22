package code.bookingState;

import code.entities.Driver;

public class CancelledState implements TripState {
    private Trip trip;

    public CancelledState(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void accept(Driver driver) {
        throw new IllegalStateException("Cannot accept a driver for a cancelled trip.");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Trip is already cancelled.");
    }

    @Override
    public void start() {
        throw new IllegalStateException("Cannot start a cancelled trip.");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete a cancelled trip.");
    }
}
