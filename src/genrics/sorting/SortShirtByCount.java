package genrics.sorting;

import java.util.Comparator;

public class SortShirtByCount implements Comparator<Shirt> {

    @Override
	public int compare(Shirt s1, Shirt s2) {
         return Long.compare(s1.getCount(), s2.getCount());
    }
}
