import java.util.Scanner;

/** Palindrome checker using iterative, recursive and array-reversal approaches. */
public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return new String(original).equals(new String(reversed));
    }

    private static String describe(boolean isPalindrome) {
        return isPalindrome ? "Palindrome" : "Not Palindrome";
    }

    public static void printResults(String text) {
        System.out.println("Iterative: " + describe(isPalindromeIterative(text))
                + " | Recursive: " + describe(isPalindromeRecursive(text))
                + " | Array Reversal: " + describe(isPalindromeArrayReversal(text)));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a word or phrase: ");
            String text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
            printResults(text);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
