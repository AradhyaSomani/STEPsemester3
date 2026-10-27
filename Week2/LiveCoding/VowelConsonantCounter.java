import java.util.Scanner;

/** Counts vowels and consonants in a book title (spaces ignored). */
public class VowelConsonantCounter {

    private static final String VOWELS = "aeiou";

    public static void countVowelsAndConsonants(String text) {
        if (text == null) {
            throw new NullPointerException("Text cannot be null.");
        }
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char current = Character.toLowerCase(text.charAt(i));
            if (current == ' ') {
                continue;
            }
            if (current >= 'a' && current <= 'z') {      // ASCII range check for letters
                if (VOWELS.indexOf(current) != -1) vowelCount++;
                else consonantCount++;
            }
        }
        System.out.println("Vowels: " + vowelCount + " | Consonants: " + consonantCount);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter book title: ");
            countVowelsAndConsonants(scanner.nextLine());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
