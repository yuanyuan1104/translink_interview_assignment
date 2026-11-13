package ca.translink.transit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RouteMap {

    private static final String ROUTES_FILE = "routes.txt";
    private static final Map<String, String> routeIdMap = new HashMap<>();
    private static final Map<String, String> routeNameMap = new HashMap<>();

    static {
        try {
            InputStream input = RouteMap.class.getClassLoader().getResourceAsStream(ROUTES_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Skip the description line at the top
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                String routeId = columns[0];
                String shortName = columns[2];
                if (!shortName.isEmpty() && !routeId.isEmpty()) {
                    routeIdMap.put(shortName, routeId);
                    routeNameMap.put(routeId, shortName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load routes file");
        }
    }

    public static String getRouteId(String shortName) {
        return routeIdMap.get(shortName);
    }

    public static String getRouteName(String routeId) {
        return routeNameMap.get(routeId);
    }
}
