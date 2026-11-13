package ca.translink.transit;

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
            String busName = RouteMap.getRouteName(routeId);
            if (busName == null) {
                busName = "";
            }

            String busId = vp.getVehicle().getId();
            if (busId == null) {
                busId = "";
            }

            System.out.println("Vehicle ID: " + busId);
            System.out.println("Bus name: " + busName);
            System.out.println("Route number: " + routeId);
            System.out.println("Direction: " + vp.getTrip().getDirectionId());
            System.out.println("Timestamp: " + vp.getTimestamp());
            System.out.println("Latitude: " + vp.getPosition().getLatitude());
            System.out.println("Longitude: " + vp.getPosition().getLongitude());
            System.out.println();
        }
    }
}
