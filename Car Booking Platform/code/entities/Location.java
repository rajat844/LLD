package code.entities;

public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
