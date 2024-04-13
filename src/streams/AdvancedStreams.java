package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Item {
	int id;
	String name;

	Item(int i, String n) {
		this.id = i;
		this.name = n;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Book {
	String title;
	double price;

	Book(String t, Double d) {
		this.title = t;
		this.price = d;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

class Person {
	String fname;
	String lname;
	int age;

	Person(String f, String l, int a) {
		this.fname = f;
		this.lname = l;
		this.age = a;
	}

	Person(String f, int a) {
		this.fname = f;
		this.lname = f;
		this.age = a;
	}

	@Override
	public String toString() {
		return "name: " + fname + " " + lname + ", age: " + age;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

public class AdvancedStreams {

	public static Optional<String> getGrade(int marks) {
		return (marks > 50) ? Optional.of("PASS") : Optional.of("FAIL");
	}

	public static void main(String[] args) {

		// https://github.com/BafS/Java8-CheatSheet
		// Q1.
		IntStream.of(0, 1, 2, 3, 4).average().ifPresent(System.out::println);
		// Q2.
		List.of(new Item(1, "Screw"), new Item(1, "Nail"), new Item(1, "Bolt"))
		.stream()
		.map(Item::getName)
		.sorted()
		.forEach(System.out::print);

		// Q3.
		System.out.println();
		Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"))
		.filter(l -> l.contains("c"))
		.flatMap(List::stream)
		.forEach(System.out::print);

		// Q4.
		System.out.println();
		System.out.println(
				IntStream.of(1, 2, 3).sum()
		);
		System.out.println(
				IntStream.of(1, 2, 3).max().getAsInt()
		);

		Stream.of(new Person("Alan", "Burke", 22), new Person("Zoe", "Peters", 20), new Person("Peter", "Castle", 29))
				.max(Comparator.comparingInt(Person::getAge))
				.ifPresent(System.out::println);

		// Q5.
		List.of(10, 47, 33, 23)
		.stream()
		.reduce(Integer::max)
		.ifPresent(System.out::println);

		System.out.println(List.of(10, 47, 33, 23).stream().reduce(0, Integer::max));
		// Q6.
		Optional<String> g1 = getGrade(50);
		Optional<String> g2 = getGrade(55);
		System.out.println(g1.orElse("UNKNOWN"));
		if (g2.isPresent()) {
			g2.ifPresent(System.out::println);
		} else {
			System.out.println("Empty");
		}
		// Optional’s are immutable.
		// Optional.of(null); // NullPointerException
		// Optional.ofNullable(null); // Optional.empty returned
		System.out.println(g1.orElse("UNKNOWN"));

		// Q7.
		Map<String, Double> bookMap = List
				.of(new Book("Atlas Shrugged", 10.0), new Book("Freedom at Midnight", 5.0),
						new Book("Gone with the wind", 5.0))
				.stream().collect(Collectors.toMap(Book::getTitle, Book::getPrice));
		bookMap.forEach((title, price) -> {
			if (title.startsWith("A")) {
				System.out.println(price);
			}
		});

		// Q8.
		List.of(new Book("Gone with the wind", 5.0), new Book("Gone with the wind", 10.0),
				new Book("Atlas Shrugged", 10.0)).stream()
				.collect(Collectors.toMap(Book::getTitle, Book::getPrice, Double::sum))
				.forEach((title, price) -> System.out.println(title + ", " + price));

		// Q9.
		System.out.println(List.of(new Person("Bob", 31), new Person("Paul", 32), new Person("John", 33)).stream()
				.filter(p -> p.getAge() < 30).mapToInt(Person::getAge).average().orElse(0));
		// Q11.
		List<String> genres = List.of(new AnotherBook("Gone with the wind", "Fiction"), new AnotherBook("Bourne Ultimatum", "Thriller"),
				new AnotherBook("The Client", "Thriller")).stream().map(AnotherBook::getGenre).distinct()
				.collect(Collectors.toList());
		System.out.println(genres);

		//Q12
		System.out.println(DoubleStream.of(0, 2, 4).filter(n -> n%2 ==0).sum());
		System.out.println(Stream.of(1.0, 3.0).mapToDouble(Double::doubleValue).filter(n->n%2==0).average().orElse(0.0));

		//Q13.
		System.out.println(Arrays.asList(11, 11, 22, 33, 33, 55, 66).stream().distinct().anyMatch(n -> n == 11));
		System.out.println(Arrays.asList(11, 11, 22, 33, 33, 55, 66).stream().distinct().noneMatch(x->x%11>0));

		//Q14.
		AtomicInteger ai = new AtomicInteger();
		Stream.of(11, 11, 22, 33).parallel().filter(n -> {
			ai.incrementAndGet();
			return n %2 ==0;
		}).count();
		System.out.println(ai);
	}

}

class AnotherBook {
	String title;
	String genre;

	AnotherBook(String t, String g) {
		this.title = t;
		this.genre = g;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
