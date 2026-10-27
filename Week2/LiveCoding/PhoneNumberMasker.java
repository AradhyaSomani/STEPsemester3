import java.util.Scanner;

/** Masks a 10-digit phone number, showing only the last 4 digits. */
public class PhoneNumberMasker {

    private static final int PHONE_LENGTH = 10;
    private static final int VISIBLE_DIGITS = 4;
    private static final String MASK = "XXXXXX";
    private static final String INVALID_MESSAGE = "Invalid phone number";

    private static boolean isAllDigits(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) < '0' || text.charAt(i) > '9') {   // ASCII digit range
                return false;
            }
        }
        return true;
    }

    public static String maskPhoneNumber(String phone) {
        if (phone == null) {
            throw new NullPointerException("Phone number cannot be null.");
        }
        if (phone.length() != PHONE_LENGTH || !isAllDigits(phone)) {
            return INVALID_MESSAGE;
        }
        StringBuilder masked = new StringBuilder(MASK);
        masked.append(phone.substring(PHONE_LENGTH - VISIBLE_DIGITS));
        masked.insert(MASK.length(), "-");
        return masked.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter phone number: ");
            System.out.println(maskPhoneNumber(scanner.nextLine().trim()));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
