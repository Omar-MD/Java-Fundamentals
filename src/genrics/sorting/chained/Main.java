package genrics.sorting.chained;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	List<Person> people = new ArrayList<>();

        people.add(new Person("Alice", 25, 170.5));
        people.add(new Person("Bob", 30, 165.0));
        people.add(new Person("Charlie", 20, 180.0));
        people.add(new Person("David", 25, 175.0));
        people.add(new Person("Eve", 20, 160.0));
        people.add(new Person("Frank", 30, 175.5));
        people.add(new Person("Grace", 25, 165.0));
        people.add(new Person("Hannah", 20, 170.0));
        people.add(new Person("Isaac", 30, 185.0));
        people.add(new Person("Jane", 25, 160.0));

        Collections.sort(people, PersonComparators.getAgeNameHeightComparator());

        people.forEach(System.out::println);
    }
}