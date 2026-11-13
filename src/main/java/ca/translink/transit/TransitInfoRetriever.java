package ca.translink.transit;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TransitInfoRetriever {

    private static final String URL = "https://gtfsapi.translink.ca/v3/gtfsposition?apikey=";

    public byte[] fetch() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + Config.getApiKey()))
                .GET()
                .build();

        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Request failed: HTTP " + response.statusCode());
        }
        return response.body();
    }
}
