package ca.translink.transit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DirectionMap {

    private static final String DIRECTIONS_FILE = "directions.txt";
    private static final Map<String, String> directionMap = new HashMap<>();

    static {
        try {
            InputStream input = DirectionMap.class.getClassLoader().getResourceAsStream(DIRECTIONS_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Skip description at the top
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                String direction = columns[0];
                String directionId = columns[1];
                String routeId = columns[2];
                String key = routeId + "," + directionId;
                directionMap.put(key, direction);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load directions file.");
        }
    }

    public static String getDirection(String routeId, int directionId) {
        return directionMap.get(routeId + "," + directionId);
    }
}
