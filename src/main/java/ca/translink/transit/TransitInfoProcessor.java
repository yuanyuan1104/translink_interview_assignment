package ca.translink.transit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class TransitInfoProcessor {
    private List<FeedEntity> entities;

    public TransitInfoProcessor(FeedMessage feed) {
        this.entities = feed.getEntityList();
    }

    public List<FeedEntity> filterByRoute(String routeNumber) {
        String routeId = RouteMap.getRouteId(routeNumber);
        List<FeedEntity> result = new ArrayList<>();
        for (FeedEntity entity : this.entities) {
            if (entity.hasVehicle() && entity.getVehicle().getTrip().getRouteId().equals(routeId)) {
                result.add(entity);
            }
        }
        return result;
    }

    public List<FeedEntity> filterUniqueBuses(List<FeedEntity> list) {
        List<FeedEntity> result = new ArrayList<>();
        Set<String> addedIds = new HashSet<>();
        for (FeedEntity entity : list) {
            String id = entity.getVehicle().getVehicle().getId();
            if (addedIds.add(id)) {
                result.add(entity);
            }
        }
        return result;
    }
}
