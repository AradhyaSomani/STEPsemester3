import java.util.Scanner;

/** Counts vowels and consonants in each word read from input. */
public class VowelConsonantWordCounter {

    private static final String VOWELS = "aeiou";

    public static int countVowels(String word) {
        int vowelCount = 0;
        for (char letter : word.toLowerCase().toCharArray()) {
            if (VOWELS.indexOf(letter) != -1) vowelCount++;
        }
        return vowelCount;
    }

    public static int countConsonants(String word) {
        int letterCount = 0;
        for (char letter : word.toCharArray()) {
            if (!Character.isLetter(letter)) {
                throw new IllegalArgumentException("Word must contain only letters: " + word);
            }
            letterCount++;
        }
        return letterCount - countVowels(word);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                try {
                    System.out.println("Word: " + word);
                    System.out.println("Vowels: " + countVowels(word));
                    System.out.println("Consonants: " + countConsonants(word));
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
