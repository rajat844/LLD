package code.bookingState;

import code.entities.Driver;

public interface TripState {
    public void accept(Driver driver);
    public void cancel();
    public void start();
    public void complete();
}

