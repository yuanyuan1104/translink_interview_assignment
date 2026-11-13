package ca.translink.transit.model;

public class Vehicle {

    private long timestamp;
    private Trip trip;
    private Position position;
    private VehicleDescription description;

    public Vehicle(long timestamp, Trip trip, Position position, VehicleDescription description) {
        this.timestamp = timestamp;
        this.trip = trip;
        this.position = position;
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Trip getTrip() {
        return trip;
    }

    public Position getPosition() {
        return position;
    }

    public VehicleDescription getDescription() {
        return description;
    }
}
