package genrics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductCounter {

	private Map<String, Integer> productCount = new HashMap<>();
	private Map<String, String> productNames = new TreeMap<>();

	public static void main(String[] args) {

		// List of part data
		String[] parts = new String[] { "1S01", "1S01", "1S01", "1S01", "1S01", "1S02", "1S02", "1S02", "1H01", "1H01",
				"1S02", "1S01", "1S01", "1H01", "1H01", "1H01", "1S02", "1S02", "1M02", "1M02", "1M02" };

		// Create Product Name Part Number map
		Map<String, String> productNames = new TreeMap<>();
		productNames.put("Blue Polo Shirt", "1S01");
		productNames.put("Black Polo Shirt", "1S02");
		productNames.put("Red Ball Cap", "1H01");
		productNames.put("Duke Mug   ", "1M02");

		ProductCounter prodCounter = new ProductCounter(productNames);
		prodCounter.processList(parts);
		prodCounter.printReport();
	}

	public ProductCounter(Map<String, String> productNames) {
		this.productNames = productNames;
	}

	public void processList(String[] list) {
		Arrays.asList(list).forEach(part -> productCount.compute(part, (key, count) -> (count == null) ? 1 : count + 1));
	}

	public void printReport() {
		System.out.println("=== Product Report ===");
		productNames
				.forEach((desc, part) -> System.out.println("Name: " + desc + "\t\tCount: " + productCount.get(part)));
	}
}