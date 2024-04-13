package lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

interface Printable<T> {
	void print(T str);
}

interface Retrievable<T> {
	T retrieve();
}

interface Evaluate<T> {
	Boolean isNegative(T c);
}

interface Functionable<T, R> {
	R applyThis(T c);
}

class Person {
	private Integer age;
	private String name;
	private Double height;

	Person(String n, Integer a, Double h) {
		this.height = h;
		this.name = n;
		this.age = a;
	}
	@Override
	public String toString() {
		return age + ", " + name + ", "+ height;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
}

public class BasicLambdas {

	public static void main(String[] args) {
//		BasicLambdas bl = new BasicLambdas();
//		bl.consumer();
//		bl.supplier();
//		bl.predicate();
//		bl.function();
//

		List<Person> listPeople = getPeople();
		sortName(listPeople);
		listPeople.forEach(System.out::println);
		sortName(listPeople);
		listPeople.forEach(System.out::println);

	}

	public static void sortName(List<Person> l) {
		l.sort(Comparator.comparing(Person::getName).reversed());
	}

	private static List<Person> getPeople() {
		List<Person> res = new ArrayList<>();
		res.add(new Person("Mike", 33, 1.8));
		res.add(new Person("Mary", 25, 1.4));
		res.add(new Person("Alan", 34, 1.7));
		res.add(new Person("Zoe", 30, 1.5));
		return res;
	}

	public void consumer() {
		Printable<String> p = str -> System.out.println(str);
		p.print("Printable Lambda");

		Consumer<String> printLambda = str -> p.print(str);
		Consumer<String> printRef = p::print;

		printLambda.accept("Consumer Printable Lambda");
		printRef.accept("Consumer Printable Ref");
	}

	public void supplier() {
		Retrievable<Integer> r = () -> 77;
		System.out.println(r.retrieve());

		IntSupplier intSup = () -> 77;
		System.out.println(intSup.getAsInt());
	}

	public void predicate() {
		Evaluate<Integer> eval = i -> i < 0;
		System.out.println(eval.isNegative(-1));
		System.out.println(eval.isNegative(1));

		IntPredicate e = i -> i < 0;
		System.out.println(e.test(-1));
		System.out.println(e.test(1));

		Predicate<Integer> isEven = n -> n % 2 == 0;
		System.out.println(check(4, isEven));
		System.out.println(check(7, isEven));

		Predicate<String> mr = str -> str.startsWith("Mr.");
		System.out.println(check("Mr. Joe Bloggs", mr));
		System.out.println(check("Ms. Ann Bloggs", mr));
	}

	public <T> boolean check(T v, Predicate<T> checkFunc) {
		return checkFunc.test(v);
	}

	public void function() {
		Functionable<Integer, String> func = i -> "Number is: " + i;
		System.out.println(func.applyThis(25));

		IntFunction<String> f = i -> "Number is:" + i;
		System.out.println(f.apply(25));
	}
}
