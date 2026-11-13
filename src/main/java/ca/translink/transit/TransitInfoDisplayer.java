package ca.translink.transit;

import java.time.Instant;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;

public class TransitInfoDisplayer {

    public void display(List<FeedEntity> buses) {
        System.out.println("Total unique buses: " + buses.size());
        System.out.println();

        for (FeedEntity entity : buses) {
            VehiclePosition vp = entity.getVehicle();

            String routeId = vp.getTrip().getRouteId();
            String routeNumber = RouteMap.getRouteName(routeId);
            if (routeNumber == null) {
                routeNumber = "";
            }

            String busId = vp.getVehicle().getId();

            String direction = DirectionMap.getDirection(routeId, vp.getTrip().getDirectionId());
            if (direction == null) {
                direction = "";
            }

            System.out.println("Vehicle ID: " + busId);
            System.out.println("Route Number: " + routeNumber);
            System.out.println("Direction: " + direction);
            System.out.println("Timestamp: " + Instant.ofEpochSecond(vp.getTimestamp()));
            System.out.println("Latitude: " + vp.getPosition().getLatitude());
            System.out.println("Longitude: " + vp.getPosition().getLongitude());
            System.out.println();
        }
    }
}
