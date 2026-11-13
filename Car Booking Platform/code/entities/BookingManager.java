package code.entities;

import java.util.ArrayList;
import java.util.List;

import code.bookingState.Trip;
import code.strategies.CancellationStrategy;
import code.strategies.DriverMatchingStrategy;
import code.strategies.FareEstimationStrategy;
import code.strategies.PaymentStrategy;

public class BookingManager {
    private FareEstimationStrategy fareStrategy;
    private DriverMatchingStrategy matchStrategy;
    private CancellationStrategy cancelStrategy;
    private PaymentStrategy paymentStrategy;
    private List<Trip> trips;
    private List<Driver> drivers;

    public BookingManager(FareEstimationStrategy fareStrategy,
            DriverMatchingStrategy matchStrategy,
            CancellationStrategy cancelStrategy, PaymentStrategy paymentStrategy) {
        this.fareStrategy = fareStrategy;
        this.matchStrategy = matchStrategy;
        this.cancelStrategy = cancelStrategy;
        this.paymentStrategy = paymentStrategy;
        this.trips = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    public Trip requestTrip(User rider, Location pickup, Location dropoff, VehicleType vehicleType) {
        double estimatedFare = fareStrategy.getFare(pickup, dropoff, vehicleType);
        System.out.println("Estimated Fare: " + estimatedFare);

        Trip trip = new Trip(rider, pickup, dropoff);
        trips.add(trip);

        Driver matchedDriver = matchStrategy.getDriver(pickup, vehicleType, drivers);

        if (matchedDriver != null) {
            trip.accept(matchedDriver);
        } else {
            System.out.println("No drivers available.");
        }

        return trip;
    }

    public void cancelTrip(Trip trip) {
        double fee = cancelStrategy.getCancelCharges(trip);
        System.out.println("Cancellation fee applied: " + fee);

        trip.cancel();

        if (fee > 0) {
            Payment panelty = new Payment(fee);
            trip.setPayment(panelty);
            if (paymentStrategy.pay(fee)) {
                panelty.setPaymentStatus(PaymentStatus.COMPLETED);
            }
        }
    }

    public void completeTrip(Trip trip, Location dropOff) {
        trip.complete();

        double finalFare = fareStrategy.getFare(trip.getPickupLocation(), dropOff,
                trip.getDriver().getVehicle().getVehicleType());

        Payment payment = new Payment(finalFare);
        trip.setPayment(payment);

        if (paymentStrategy.pay(finalFare)) {
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            trip.getDriver().changeStatus(DriverStatus.AVAILABLE);
        }
    }
}
