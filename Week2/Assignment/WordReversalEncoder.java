import java.util.Scanner;

/** Reverses every word of a sentence while keeping the word order. */
public class WordReversalEncoder {

    private static final String WORD_SEPARATOR = " ";

    private static String reverseWord(String word) {
        StringBuilder reversed = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed.append(word.charAt(i));
        }
        return reversed.toString();
    }

    public static String reverseEachWord(String sentence) {
        if (sentence == null) {
            throw new NullPointerException("Sentence cannot be null.");
        }
        String[] words = sentence.split(WORD_SEPARATOR);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(WORD_SEPARATOR);
            result.append(reverseWord(words[i]));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter sentence: ");
            System.out.println(reverseEachWord(scanner.nextLine()));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
