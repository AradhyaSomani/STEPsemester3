import java.util.Scanner;

/** Reverses each word with StringBuilder and reports whether it is a palindrome. */
public class WordReverserPalindromeChecker {

    public static String reverseWord(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    public static boolean isPalindrome(String word) {
        return word.equals(reverseWord(word));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                String reversed = reverseWord(word);
                String verdict = isPalindrome(word) ? "palindrome" : "not a palindrome";
                System.out.println(reversed + " - " + verdict);
            }
        }
    }
}
