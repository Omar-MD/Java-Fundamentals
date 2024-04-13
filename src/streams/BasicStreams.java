package streams;

import java.util.List;
import java.util.Map;

public class BasicStreams {
	public static void main(String[] args) {

		List<Integer> nl = List.of(9, 10, 13, 76, 8, -9);
		System.out.println(nl.stream().filter(i -> i< 10).count());
		System.out.println(List.of("Andrew", "Brian", "Charlie", "Aaron", "Ed").stream().map(String::toUpperCase).filter(str -> str.startsWith("A")).count());
		System.out.println(List.of(98.4, 100.2, 87.9, 102.8).stream().filter(i -> i > 100).count());
		System.out.println(Map.of("Spot", 1, "Charlie", 8).entrySet().stream().filter(i -> i.getValue() > 4).count());

		List<String> names = List.of("Andrew", "Brian", "Charlie", "Aaron", "Ed", "Sean");
		boolean bool = names.stream().map(String::toUpperCase).anyMatch(str -> str.startsWith("S"));
		if(bool) {
			System.out.println(names.stream().map(String::toUpperCase).filter(str -> str.startsWith("S")).count());
		}

		System.out.println(List.of(new Purchase("Shirt", 100.1), new Purchase("Shoes", 149.9), new Purchase("Jumper", 90.3))
				.stream()
				.mapToDouble(p -> p.getPrice())
				.filter(price -> price > 100 && price < 200)
				.average()
				.orElse(0)
		);
	}
}

class Purchase {
	String item;
	double price;

	Purchase(String i, double p) {
		this.item = i;
		this.price = p;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
