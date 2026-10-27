import java.util.Scanner;

/** Normalizes and validates library codes: 3 letters + 4 digit year + 6 digit catalog number. */
public class IsbnNormalizerValidator {

    private static final int CODE_LENGTH = 13;
    private static final int PUBLISHER_CODE_LENGTH = 3;
    private static final int YEAR_END_INDEX = 7;

    public static String normalizeCode(String raw) {
        if (raw == null) {
            throw new NullPointerException("Code cannot be null.");
        }
        String trimmed = raw.trim();
        if (trimmed.length() < PUBLISHER_CODE_LENGTH) {
            return trimmed;
        }
        return trimmed.substring(0, PUBLISHER_CODE_LENGTH).toUpperCase()
                + trimmed.substring(PUBLISHER_CODE_LENGTH);
    }

    public static String validateAndFormat(String code) {
        if (code.length() != CODE_LENGTH) {
            return "Invalid: code must be exactly 13 characters";
        }
        for (int i = 0; i < PUBLISHER_CODE_LENGTH; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = PUBLISHER_CODE_LENGTH; i < CODE_LENGTH; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: year and catalog number must be digits";
            }
        }
        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(code, 0, PUBLISHER_CODE_LENGTH).append("] YEAR: ")
                .append(code, PUBLISHER_CODE_LENGTH, YEAR_END_INDEX)
                .append(" | CATALOG: ").append(code.substring(YEAR_END_INDEX));
        return formatted.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter library code: ");
            String normalized = normalizeCode(scanner.nextLine());
            System.out.println(validateAndFormat(normalized));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
