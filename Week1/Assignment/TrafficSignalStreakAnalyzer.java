import java.util.Scanner;

/** Finds the longest streak of the same signal colour in a log. */
public class TrafficSignalStreakAnalyzer {

    private static final String VALID_SIGNALS = "RYG";

    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            throw new IllegalArgumentException("Signal log cannot be empty.");
        }
        String log = signalLog.toUpperCase();
        char longestColor = log.charAt(0);
        int longestLength = 0;
        int currentLength = 0;

        for (int i = 0; i < log.length(); i++) {
            char current = log.charAt(i);
            if (VALID_SIGNALS.indexOf(current) == -1) {
                throw new IllegalArgumentException("Invalid signal '" + current + "' at position " + (i + 1));
            }
            currentLength = (i > 0 && current == log.charAt(i - 1)) ? currentLength + 1 : 1;
            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestColor = current;
            }
        }
        System.out.println("Longest Streak: '" + longestColor + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter signal log (R/Y/G): ");
            findLongestStreak(scanner.nextLine().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
