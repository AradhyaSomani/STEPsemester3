import java.util.Scanner;

/** Compares typed text with the original passage character by character. */
public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        if (original.length() != typed.length()) {
            throw new IllegalArgumentException("Both strings must have equal length.");
        }
        if (original.isEmpty()) {
            throw new IllegalArgumentException("Passage cannot be empty.");
        }
        int matched = 0;
        int firstMismatch = -1;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }
        double accuracy = (matched * 100.0) / original.length();
        String mismatchInfo = (firstMismatch == -1)
                ? "No Mismatches"
                : "First Mismatch at position " + (firstMismatch + 1)
                  + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')";
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | %s%n",
                matched, original.length(), accuracy, mismatchInfo);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter original passage: ");
            String original = scanner.nextLine();
            System.out.print("Enter typed text: ");
            String typed = scanner.nextLine();
            checkTypingAccuracy(original, typed);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
