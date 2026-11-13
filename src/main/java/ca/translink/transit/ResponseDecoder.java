package ca.translink.transit;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class ResponseDecoder {

    public FeedMessage decode(byte[] data) throws Exception {
        return FeedMessage.parseFrom(data);
    }
}
