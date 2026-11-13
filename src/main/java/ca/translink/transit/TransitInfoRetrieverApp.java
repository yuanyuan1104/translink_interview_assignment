package ca.translink.transit;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class TransitInfoRetrieverApp {
	private static final String LINE99 = "099";

    public static void main(String[] args) {
    		TransitInfoRetriever retriever = new TransitInfoRetriever();
        try {
//			byte[] data = retriever.fetch();
//			ResponseDecoder decoder = new ResponseDecoder();
//	        FeedMessage feed = decoder.decode(data);
//			System.out.println(feed);
 
	        System.out.println(RouteMap.getRouteId(LINE99));
		} catch (Exception e) {
			 e.printStackTrace();
		}
    }
}
