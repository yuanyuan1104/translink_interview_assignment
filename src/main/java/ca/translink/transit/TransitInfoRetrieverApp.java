package ca.translink.transit;

import java.util.List;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class TransitInfoRetrieverApp {
	private static final String LINE99 = "099";

    public static void main(String[] args) {
    		TransitInfoRetriever retriever = new TransitInfoRetriever();
        try {
			byte[] data = retriever.fetch();
			ResponseDecoder decoder = new ResponseDecoder();
	        FeedMessage feed = decoder.decode(data);
	        TransitInfoProcessor processor = new TransitInfoProcessor(feed);
	        List<FeedEntity> onRoute = processor.filterByRoute(LINE99);
            List<FeedEntity> uniqueBuses = processor.filterUniqueBuses(onRoute);
            TransitInfoDisplayer displayer = new TransitInfoDisplayer();
            displayer.display(uniqueBuses);
		} catch (Exception e) {
			 e.printStackTrace();
		}
    }
}
