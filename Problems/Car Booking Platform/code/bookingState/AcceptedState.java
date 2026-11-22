package code.bookingState;

import code.entities.Driver;
import code.entities.TripStatus;

public class AcceptedState implements TripState {
    private Trip trip;

    public AcceptedState(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void accept(Driver driver) {
        throw new IllegalStateException("Trip already accepted by a driver.");
    }

    @Override
    public void cancel() {
        trip.setTripStatus(TripStatus.CANCELLED);
        trip.setState(new CancelledState(trip));
    }

    @Override
    public void start() {
        trip.setTripStatus(TripStatus.IN_PROGRESS);
        trip.setState(new InProgressState(trip));
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete a trip that hasn't started.");
    }
}
