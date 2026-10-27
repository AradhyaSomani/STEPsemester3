import java.util.Locale;
import java.util.Scanner;

/** Canteen billing: each customer type calculates its own final amount. */
public class CanteenBillingCounter {

    private static final double GUEST_SERVICE_CHARGE = 10.0;

    abstract static class Customer {
        protected final double billAmount;
        Customer(double billAmount) { this.billAmount = billAmount; }
        abstract String getType();
        abstract double calculateFinalAmount();
    }

    static class Student extends Customer {
        Student(double a) { super(a); }
        String getType() { return "STUDENT"; }
        double calculateFinalAmount() { return billAmount * 0.90; }
    }

    static class Staff extends Customer {
        Staff(double a) { super(a); }
        String getType() { return "STAFF"; }
        double calculateFinalAmount() { return billAmount * 0.95; }
    }

    static class Guest extends Customer {
        Guest(double a) { super(a); }
        String getType() { return "GUEST"; }
        double calculateFinalAmount() { return billAmount + GUEST_SERVICE_CHARGE; }
    }

    static Customer createCustomer(String type, double amount) {
        switch (type.toUpperCase()) {
            case "STUDENT": return new Student(amount);
            case "STAFF": return new Staff(amount);
            case "GUEST": return new Guest(amount);
            default: throw new IllegalArgumentException("Unknown customer type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double grandTotal = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Customer customer = createCustomer(parts[0], Double.parseDouble(parts[1]));
                    double amount = customer.calculateFinalAmount();
                    grandTotal += amount;
                    System.out.printf("%s: %.2f%n", customer.getType(), amount);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
