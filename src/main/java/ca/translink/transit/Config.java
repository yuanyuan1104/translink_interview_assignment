package ca.translink.transit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties.");
        }
    }

    public static String getApiKey() {
        String apiKey = properties.getProperty("apikey");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("No API Key found.");
        }
        return apiKey;
    }
}
