package ca.translink.transit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class TransitApiTest {

    private static final String URL = "https://gtfsapi.translink.ca/v3/gtfsposition?apikey=";
    private static final String LINE99 = "099";

    // The API returns a successful HTTP response
    @Test
    public void testSuccessfulResponse() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + Config.getApiKey()))
                .GET()
                .build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        assertEquals(200, response.statusCode(), "Expected HTTP 200 but got " + response.statusCode());
    }

    // The response can be decoded as a valid FeedMessage
    @Test
    public void testResponseDecode() throws Exception {
        try {
            TransitInfoRetriever retriever = new TransitInfoRetriever();
            byte[] data = retriever.fetch();
            ResponseDecoder decoder = new ResponseDecoder();
            FeedMessage feed = decoder.decode(data);
            assertNotNull(feed, "Decoded FeedMessage should not be null");
        } catch (Exception e) {
            fail("Response could not be decoded as a valid FeedMessage");
        }
    }

    // The Route 99 vehicle list contains no duplicate vehicle IDs
    @Test
    public void testNoDuplicateID() throws Exception {
        List<FeedEntity> uniqueBuses;
        try {
            TransitInfoRetriever retriever = new TransitInfoRetriever();
            byte[] data = retriever.fetch();
            ResponseDecoder decoder = new ResponseDecoder();
            FeedMessage feed = decoder.decode(data);

            TransitInfoProcessor processor = new TransitInfoProcessor(feed);
            List<FeedEntity> onRoute = processor.filterByRoute(LINE99);
            uniqueBuses = processor.filterUniqueBuses(onRoute);
        } catch (Exception e) {
            fail("Could not retrieve and process route 99 data");
            return;
        }

        Set<String> seenIds = new HashSet<>();
        for (FeedEntity entity : uniqueBuses) {
            String id = entity.getVehicle().getVehicle().getId();
            if (!seenIds.add(id)) {
                fail("Duplicate vehicle ID found: " + id);
            }
        }
    }
}
