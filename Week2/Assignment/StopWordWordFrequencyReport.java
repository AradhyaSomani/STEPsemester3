import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Word-frequency report for feedback text with common stop words removed. */
public class StopWordWordFrequencyReport {

    private static final String[] STOP_WORDS = {"the", "was", "and", "a", "is", "of", "in"};

    private static boolean isStopWord(String word) {
        for (String stopWord : STOP_WORDS) {
            if (stopWord.equals(word)) return true;
        }
        return false;
    }

    private static String normalizeText(String feedback) {
        return feedback.toLowerCase().replace(".", "").replace(",", "")
                .replace("!", "").replace("?", "").replace(";", "").replace(":", "").trim();
    }

    public static Map<String, Integer> countWordFrequency(String feedback) {
        Map<String, Integer> frequency = new LinkedHashMap<>();   // keeps first-seen order for ties
        String cleaned = normalizeText(feedback);
        if (cleaned.isEmpty()) {
            return frequency;
        }
        for (String word : cleaned.split("\\s+")) {
            if (!isStopWord(word)) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }
        return frequency;
    }

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null) {
            throw new NullPointerException("Feedback cannot be null.");
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(countWordFrequency(feedback).entrySet());
        entries.sort((first, second) -> second.getValue() - first.getValue());   // stable sort
        if (entries.isEmpty()) {
            System.out.println("No meaningful words found.");
            return;
        }
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter feedback paragraph: ");
            printFilteredWordFrequency(scanner.nextLine());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
