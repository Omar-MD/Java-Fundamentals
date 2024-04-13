package genrics.maps;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class MapsToMaps {

	public static void main(String[] args) {
		Map<String, Integer> channelToSubscribers = new TreeMap<>();
		Map<String, String> channelToPublisher = new TreeMap<>();
		Map<String, Integer> publisherToSubscribers = new TreeMap<>();

		channelToSubscribers.put("JustForLaughs", 120_000);
		channelToSubscribers.put("JustForGags", 10_000);
		channelToSubscribers.put("ContemplationTechniques", 10_000);
		channelToSubscribers.put("A New Earth", 20_000);

		channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
		channelToPublisher.put("JustForGags", "Charlie Chaplin");
		channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
		channelToPublisher.put("A New Earth", "Echhart Tolle");

		channelToSubscribers.forEach((channel, subscribers) -> {
		    String publisher = channelToPublisher.get(channel);
		    publisherToSubscribers.merge(publisher, subscribers, Integer::sum);
		});

		publisherToSubscribers.forEach((p, s)-> System.out.println("Publisher: " + p +  "; No. of Subscribers: " + s));
		int minSubs = Collections.min(publisherToSubscribers.values());
		int maxSubs = Collections.max(publisherToSubscribers.values());
		publisherToSubscribers.forEach((p, s)-> {
			if(s == maxSubs) {
				System.out.println("Publisher with most subs: " + p +  "; No. of Subscribers: " + s);
			}else if (s == minSubs) {
				System.out.println("Publisher with least subs: " + p +  "; No. of Subscribers: " + s);
			}
		});

		   // Calculate publisher with most and least subscribers
        Map.Entry<String, Integer> maxEntry = Collections.max(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue());
        Map.Entry<String, Integer> minEntry = Collections.min(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue());

        // Output publisher with most and least subscribers
        System.out.println("Publisher with most subscribers: " + maxEntry.getKey() + " " + maxEntry.getValue());
        System.out.println("Publisher with fewest subscribers: " + minEntry.getKey() + " " + minEntry.getValue());
	}

}
