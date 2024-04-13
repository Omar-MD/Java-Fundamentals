package localisation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

public class DateApplication {

	PrintWriter pw = new PrintWriter(System.out, true);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Locale irlLocale = new Locale("en", "IE");
	Locale currentLocale = irlLocale;
	ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

	public static void main(String[] args) throws ParseException {
		DateApplication dateApp = new DateApplication();
		dateApp.run();
//		dateApp.java8();
	}

	public void java8() {
		// A.
		LocalDate startWW2 = LocalDate.of(1939, Month.SEPTEMBER, 1);
		LocalDate endWW2 = LocalDate.of(1945, Month.SEPTEMBER, 2);
		System.out.println("Started: " + startWW2.getDayOfWeek() + "\nEnded: " + endWW2.getDayOfWeek());

		// B.
		LocalDate now = LocalDate.now();
		LocalDate joeDOB = LocalDate.of(2000, Month.FEBRUARY, 12);
		LocalDate anaDOB = LocalDate.of(2010, Month.DECEMBER, 20);
		LocalDate eighteenYearsAgo = now.minus(18, ChronoUnit.YEARS);

		Function<LocalDate, String> olderThan18 = (dob) -> dob.isBefore(eighteenYearsAgo) ? "Yes, old enough."
				: "No, too young.";

		System.out.println("To be served, you must be born before: " + eighteenYearsAgo);
		System.out.println("Joe Bloggs: " + olderThan18.apply(joeDOB));
		System.out.println("Ana Bloggs: " + olderThan18.apply(anaDOB));

		// C.
		LocalTime sundayAwake = LocalTime.of(6, 30).plus(1, ChronoUnit.HOURS);
		System.out.println(sundayAwake);
		System.out.println(sundayAwake.minus(8, ChronoUnit.HOURS));

		// D.
		ZoneId zoneTripoli = ZoneId.of("Africa/Tripoli");
		ZoneId zoneDublin = ZoneId.of("Europe/Dublin");
		LocalDateTime nowDT = LocalDateTime.now();
		ZonedDateTime dublinDT = nowDT.atZone(zoneDublin);
		ZonedDateTime tripoliDT = dublinDT.withZoneSameInstant(zoneTripoli);

		System.out.println(nowDT);
		System.out.println(dublinDT);
		System.out.println(tripoliDT);

		// E.
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss z");
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(dublinDT.format(fmt1));
		System.out.println(dublinDT.format(fmt2));

		// F.
		List<LocalDate> dates = Arrays.asList(LocalDate.of(2022, 3, 15), LocalDate.of(2023, 7, 25),
				LocalDate.of(2021, 12, 10));

		// Sorting dates
		System.out.println("Before sorting:");
		dates.forEach(System.out::println);

		Collections.sort(dates);

		System.out.println("\nAfter sorting:");
		dates.forEach(System.out::println);

		// Finding the difference between dates
		LocalDate startDate = LocalDate.of(2021, 6, 1);
		LocalDate endDate = LocalDate.of(2021, 6, 10);

		long daysBetween = Math.abs(startDate.until(endDate).getDays());
		System.out.println("\nDays between " + startDate + " and " + endDate + ": " + daysBetween);
	}

	public void run() throws ParseException {
		String line = "";

		while (!(line.equals("q"))) {
			this.printMenu();
			try {
				line = this.br.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}

			switch (line) {
			case "1":
				setEnglish();
				break;
			case "2":
				setFrench();
				break;
			case "3":
				setChinese();
				break;
			case "4":
				setRussian();
				break;
			default:
				setEnglish();
			}
		}
	}

	public void printMenu() {
		pw.println("=== Date App ===");
		pw.println("\n--- Choose Language Option ---");
		pw.println("1. " + messages.getString("menu1"));
		pw.println("2. " + messages.getString("menu2"));
		pw.println("3. " + messages.getString("menu3"));
		pw.println("4. " + messages.getString("menu4"));
		pw.println("q. " + messages.getString("menuq"));
		System.out.print(messages.getString("menucommand") + " ");
	}

	public void setEnglish() throws ParseException {
		currentLocale = new Locale("en", "IE");

		// Locale
		Locale arabicLY = new Locale.Builder().setLanguage("ar").setRegion("LY").build();
		System.out.println(arabicLY.getDisplayLanguage());
		System.out.println(arabicLY.getDisplayCountry());

		// Number
		double n = 77_000.11;
		System.out.println(NumberFormat.getNumberInstance(currentLocale).format(n));
		System.out.println(NumberFormat.getNumberInstance(arabicLY).format(n));
		System.out.println(NumberFormat.getNumberInstance(arabicLY).parse("77.000,11"));

		// Currency
		System.out.println(NumberFormat.getCurrencyInstance(currentLocale).format(n));
		System.out.println(NumberFormat.getCurrencyInstance(arabicLY).format(n));
		System.out.println(NumberFormat.getCurrencyInstance(arabicLY).parse("د.ل.‏ 77.000,110"));

		// Custom Format
		System.out.println(new DecimalFormat("$#, ###, ###.#").format(n));
		System.out.println(new DecimalFormat("$0, 000, 000.0").format(n));

		// DateTimeFormatter
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime ldft = LocalDateTime.now(zoneId);
		System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLY).format(ldft));
		System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(arabicLY).format(ldft));

		// Defaults
		Locale defLocale = Locale.getDefault();
		System.out.println(defLocale.getDisplayName());
		System.out.println(defLocale.getDisplayLanguage());
		System.out.println(defLocale.getDisplayCountry());

		Locale.setDefault(arabicLY); // Changed both display, and format
		Locale.setDefault(Locale.Category.DISPLAY, arabicLY); // Changed display only to Ar - LY
		Locale.setDefault(Locale.Category.FORMAT, arabicLY); // Changed Format only to Ar - LY


		// Parse Number
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	}

	public void setFrench() {
		currentLocale = new Locale("fr", "FR");
		messages = ResourceBundle.getBundle("localisation.MessagesBundle", currentLocale);
	}

	public void setChinese() {
		currentLocale = new Locale("zh", "CN");
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	}

	public void setRussian() {
		currentLocale = new Locale("ru", "RU");
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	}
}
