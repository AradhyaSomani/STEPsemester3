import java.util.Scanner;

/** Checks that an ATM PIN is exactly 4 characters long. */
public class AtmPinLengthValidator {

    private static final int REQUIRED_PIN_LENGTH = 4;

    public static void checkPinLength(String pin) {
        if (pin == null) {
            throw new NullPointerException("PIN cannot be null.");
        }
        if (pin.length() != REQUIRED_PIN_LENGTH) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter PIN: ");
            checkPinLength(scanner.nextLine());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
