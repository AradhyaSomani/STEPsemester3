import java.util.Scanner;

/** Reverses a customer's name while leaving the original unchanged. */
public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {
        if (customerName == null) {
            throw new NullPointerException("Customer name cannot be null.");
        }
        char[] characters = customerName.toCharArray();
        StringBuilder reversedName = new StringBuilder();
        for (int i = characters.length - 1; i >= 0; i--) {
            reversedName.append(characters[i]);
        }
        return reversedName.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine().trim();
            String reversedName = reverseCustomerName(customerName);
            System.out.println("Original Name: " + customerName);
            System.out.println("Reversed Name: " + reversedName);
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
