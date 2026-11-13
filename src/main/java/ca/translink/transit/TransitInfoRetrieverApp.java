package ca.translink.transit;

public class TransitInfoRetrieverApp {

    public static void main(String[] args) {
    		TransitInfoRetriever retriever = new TransitInfoRetriever();
        try {
			byte[] data = retriever.fetch();
			System.out.println(new String(data));
		} catch (Exception e) {
			 e.printStackTrace();
		}
    }
}
