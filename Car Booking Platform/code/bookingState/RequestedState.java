package code.bookingState;

import code.entities.Driver;
import code.entities.DriverStatus;
import code.entities.TripStatus;

public class RequestedState implements TripState {
    private Trip trip;

    public RequestedState(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void accept(Driver driver) {
        trip.setDriver(driver);
        driver.changeStatus(DriverStatus.IN_RIDE);
        trip.setTripStatus(TripStatus.ACCEPTED);
        trip.setState(new AcceptedState(trip));
        System.out.println("Driver Assigned.");
    }

    @Override
    public void cancel() {
        trip.setTripStatus(TripStatus.CANCELLED);
        trip.setState(new CancelState(trip));
    }

    @Override
    public void start() {
        throw new IllegalStateException("Cannot start a trip before a driver accepts it.");
    }

    @Override
    public void complete() {
        throw new IllegalStateException("Cannot complete an unstarted trip.");
    }
}
