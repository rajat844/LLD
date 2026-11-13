package code.bookingState;

import code.entities.Driver;
import code.entities.TripStatus;

public class InProgressState implements TripState {
    private Trip trip;

    public InProgressState(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void accept(Driver driver) {
        throw new IllegalStateException("Trip is already in progress. Cannot accept a new driver.");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel a trip that is currently in progress.");
    }

    @Override
    public void start() {
        throw new IllegalStateException("Trip has already started.");
    }

    @Override
    public void complete() {
        trip.setTripStatus(TripStatus.COMPLETED);
        trip.setState(new CompletedState(trip)); 
        System.out.println("Trip completed. Awaiting payment.");
    }
}
