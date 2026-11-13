package code.bookingState;

import java.util.UUID;

import code.entities.Driver;
import code.entities.Location;
import code.entities.Payment;
import code.entities.TripStatus;
import code.entities.User;

public class Trip {
    private UUID id;
    private User rider;
    private Driver driver;
    private Location pickup;
    private Location dropoff;
    private TripState currentState;
    private Payment payment;
    private TripStatus tripStatus;

    public Trip(User rider, Location pickup, Location dropoff) {
        this.id = UUID.randomUUID();
        this.rider = rider;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.currentState = new RequestedState(this);
        this.tripStatus = TripStatus.REQUESTED;
    }

    public void setState(TripState state) {
        this.currentState = state;
    }

    public void setTripStatus(TripStatus status) {
        this.tripStatus = status;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void accept(Driver driver) {
        currentState.accept(driver);
    }

    public void cancel() {
        currentState.cancel();
    }

    public void start() {
        currentState.start();
    }

    public void complete() {
        currentState.complete();
    }
}
