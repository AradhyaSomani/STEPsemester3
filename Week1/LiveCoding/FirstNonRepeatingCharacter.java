import java.util.Scanner;

/** Finds the first character that appears exactly once in the input. */
public class FirstNonRepeatingCharacter {

    private static final int ASCII_SIZE = 256;
    private static final char NOT_FOUND = '\0';

    /** Returns the first non-repeating char, or '\0' if none exists. */
    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[ASCII_SIZE];
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (current >= ASCII_SIZE) {
                throw new IllegalArgumentException("Non-ASCII character found: " + current);
            }
            frequency[current]++;          // char used as ASCII index
        }
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return NOT_FOUND;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a word or sentence: ");
            String text = scanner.nextLine();
            char result = findFirstNonRepeatingChar(text);
            if (result == NOT_FOUND) {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: '" + result + "'");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
