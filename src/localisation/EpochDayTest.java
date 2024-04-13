package localisation;
import java.time.LocalDate;
import java.time.Month;

public class EpochDayTest {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(1970, Month.JANUARY, 10);
        System.out.println(d1);// 1970-01-10

        // Add 5 days
        LocalDate d2 = d1.plusDays(5);
        System.out.println(d2);// 1970-01-15

        // How many days back to the Epoch Day (1/1/1970)?
        long backToEpoch = d2.toEpochDay();
        System.out.println(backToEpoch);// 14

        // Going forward from the Epoch Day (as the number is +ve),
        // what date do we land on?
        long fromEpoch = backToEpoch;
        LocalDate d3 = LocalDate.ofEpochDay(fromEpoch);
        System.out.println(d3);// 1970-01-15
    }

}