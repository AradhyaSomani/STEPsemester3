import java.util.Scanner;

/** Classifies words of a review as Short (1-4), Medium (5-8) or Long (9+). */
public class MovieReviewWordLengthProfiler {

    private static final int SHORT_MAX = 4;
    private static final int MEDIUM_MAX = 8;

    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            throw new IllegalArgumentException("Review cannot be empty.");
        }
        String[] words = review.trim().split("\\s+");
        int shortCount = 0, mediumCount = 0, longCount = 0;

        for (String word : words) {
            int length = word.replaceAll("[^A-Za-z0-9]", "").length();  // ignore punctuation
            if (length == 0) continue;
            if (length <= SHORT_MAX) shortCount++;
            else if (length <= MEDIUM_MAX) mediumCount++;
            else longCount++;
        }
        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter movie review: ");
            classifyWordLengths(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
