package genrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalculateMode {
	private static Scanner sc = new Scanner(System.in);

	public static int populateMap(int numberOfItems, Map<Integer, Integer> map) {

		int hFreq = Integer.MIN_VALUE;
		while(numberOfItems > 0) {
			int key = sc.nextInt();
			map.compute(key, (k, v) -> (v == null)? 1: v+1);

			if(hFreq < map.get(key)) {
				hFreq = map.get(key);
			}
			numberOfItems--;
		}
		return hFreq;
	}

	public static void main(String[] args) {

		System.out.println("Enter number of data items: ");
		int n = sc.nextInt();
		Map<Integer, Integer> nMap = new HashMap<>();

		System.out.println("Enter the Data");
		int hFreq = populateMap(n, nMap);

		nMap.forEach((k,v)->{
			if(v == hFreq) {
				System.out.println(k + " occurs " + v + " times.");
			}
		});
	}
}
