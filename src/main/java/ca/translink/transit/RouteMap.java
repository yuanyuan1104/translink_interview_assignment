package ca.translink.transit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RouteMap {

    private static final String ROUTES_FILE = "routes.txt";
    private static final Map<String, String> routeMap = new HashMap<>();

    static {
        try (InputStream input = RouteMap.class.getClassLoader().getResourceAsStream(ROUTES_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
        		// Skip the description line at the top
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                String routeId = columns[0];
                String shortName = columns[2];
                routeMap.put(shortName, routeId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load routes file");
        }
    }

    public static String getRouteId(String shortName) {
        return routeMap.get(shortName);
    }
}
