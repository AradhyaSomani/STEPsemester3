import java.util.Scanner;

/** Detects leap years. */
public class LeapYearDetector {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                try {
                    System.out.println(isLeapYear(Integer.parseInt(token)) ? "Leap year" : "Not a leap year");
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + token + "' is not a valid year.");
                }
            }
        }
    }
}
