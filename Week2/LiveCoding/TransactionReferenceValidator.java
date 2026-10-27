import java.util.Scanner;

/** Normalizes and validates a bank transaction reference (3 letters + 6 digit date + 5 digit sequence). */
public class TransactionReferenceValidator {

    private static final int REFERENCE_LENGTH = 14;
    private static final int BANK_CODE_LENGTH = 3;
    private static final int DATE_END_INDEX = 9;

    public static String normalizeReference(String raw) {
        if (raw == null) {
            throw new NullPointerException("Reference cannot be null.");
        }
        String trimmed = raw.trim();
        if (trimmed.length() < BANK_CODE_LENGTH) {
            return trimmed;
        }
        return trimmed.substring(0, BANK_CODE_LENGTH).toUpperCase()
                + trimmed.substring(BANK_CODE_LENGTH);
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != REFERENCE_LENGTH) {
            return "Invalid: reference must be exactly 14 characters";
        }
        for (int i = 0; i < BANK_CODE_LENGTH; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = BANK_CODE_LENGTH; i < REFERENCE_LENGTH; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: date and sequence must be digits";
            }
        }
        String date = reference.substring(BANK_CODE_LENGTH, DATE_END_INDEX);
        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(reference, 0, BANK_CODE_LENGTH).append("] DATE: ")
                .append(date, 0, 2).append("/").append(date, 2, 4).append("/").append(date, 4, 6)
                .append(" | SEQ: ").append(reference.substring(DATE_END_INDEX));
        return formatted.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter transaction reference: ");
            String normalized = normalizeReference(scanner.nextLine());
            System.out.println(validateAndFormat(normalized));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
