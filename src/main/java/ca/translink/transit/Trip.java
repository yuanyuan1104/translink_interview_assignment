package ca.translink.transit;

public class Trip {

    private String tripId;
    private String routeId;
    private int directionId;

    public Trip(String tripId, String routeId, int directionId) {
        this.tripId = tripId;
        this.routeId = routeId;
        this.directionId = directionId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getRouteId() {
        return routeId;
    }

    public int getDirectionId() {
        return directionId;
    }
}
