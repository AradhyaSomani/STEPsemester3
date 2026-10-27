import java.util.Locale;
import java.util.Scanner;

/** Applies a payment-type specific fee to every transaction through polymorphism. */
public class PaymentFeeCalculator {

    abstract static class Payment {
        protected final double amount;
        Payment(double amount) { this.amount = amount; }
        abstract String getType();
        abstract double calculateFinalAmount();
    }

    static class CardPayment extends Payment {
        private static final double FEE_RATE = 0.02;
        CardPayment(double amount) { super(amount); }
        String getType() { return "CARD"; }
        double calculateFinalAmount() { return amount + amount * FEE_RATE; }
    }

    static class WalletPayment extends Payment {
        private static final double FEE_RATE = 0.01;
        WalletPayment(double amount) { super(amount); }
        String getType() { return "WALLET"; }
        double calculateFinalAmount() { return amount + amount * FEE_RATE; }
    }

    static class BankTransferPayment extends Payment {
        BankTransferPayment(double amount) { super(amount); }
        String getType() { return "BANKTRANSFER"; }
        double calculateFinalAmount() { return amount; }
    }

    static Payment createPayment(String type, double amount) {
        switch (type.toUpperCase()) {
            case "CARD": return new CardPayment(amount);
            case "WALLET": return new WalletPayment(amount);
            case "BANKTRANSFER": return new BankTransferPayment(amount);
            default: throw new IllegalArgumentException("Unknown payment type: " + type);
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
                    Payment payment = createPayment(parts[0], Double.parseDouble(parts[1]));
                    double finalAmount = payment.calculateFinalAmount();
                    grandTotal += finalAmount;
                    System.out.printf("%s: %.2f%n", payment.getType(), finalAmount);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "' (" + e.getMessage() + ")");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}
