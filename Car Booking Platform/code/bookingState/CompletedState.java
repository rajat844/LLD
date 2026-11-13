package code.bookingState;

import code.entities.Driver;

public class CompletedState implements TripState {
    private Trip trip;

    public CompletedState(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void accept(Driver driver) {
        throw new IllegalStateException("Cannot accept a driver for a completed trip.");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Trip is already completed.");
    }

    @Override
    public void start() {
        throw new IllegalStateException("Cannot start a completed trip.");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete a completed trip.");
    }
}
