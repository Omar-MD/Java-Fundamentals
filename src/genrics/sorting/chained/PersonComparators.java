package genrics.sorting.chained;

import java.util.Comparator;

public class PersonComparators {
	private PersonComparators() {}
	public static Comparator<Person> getAgeNameHeightComparator(){
		Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
        Comparator<Person> heightComparator = Comparator.comparingDouble(Person::getHeight).reversed();

		return ageComparator.thenComparing(nameComparator).thenComparing(heightComparator);
	}
}
