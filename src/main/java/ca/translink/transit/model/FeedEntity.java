package ca.translink.transit.model;

public class FeedEntity {

    private String id;
    private Vehicle vehicle;

    public FeedEntity(String id, Vehicle vehicle) {
        this.id = id;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
