import java.util.Locale;
import java.util.Scanner;

/** Calculates delivery fees per delivery type through polymorphism. */
public class DeliveryFeeCalculator {

    abstract static class Delivery {
        protected final double weight;
        protected final double distance;
        Delivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
        abstract String getType();
        abstract double calculateFee();
    }

    static class StandardDelivery extends Delivery {
        StandardDelivery(double w, double d) { super(w, d); }
        String getType() { return "STANDARD"; }
        double calculateFee() { return 5 + 0.50 * weight + 0.10 * distance; }
    }

    static class ExpressDelivery extends Delivery {
        ExpressDelivery(double w, double d) { super(w, d); }
        String getType() { return "EXPRESS"; }
        double calculateFee() { return 15 + 1.00 * weight + 0.20 * distance; }
    }

    static class InternationalDelivery extends Delivery {
        private final double customsFee;
        InternationalDelivery(double w, double d, double customsFee) { super(w, d); this.customsFee = customsFee; }
        String getType() { return "INTERNATIONAL"; }
        double calculateFee() { return 25 + 2.00 * weight + 0.50 * distance + customsFee; }
    }

    static Delivery createDelivery(String[] p) {
        double weight = Double.parseDouble(p[1]);
        double distance = Double.parseDouble(p[2]);
        switch (p[0].toUpperCase()) {
            case "STANDARD": return new StandardDelivery(weight, distance);
            case "EXPRESS": return new ExpressDelivery(weight, distance);
            case "INTERNATIONAL": return new InternationalDelivery(weight, distance, Double.parseDouble(p[3]));
            default: throw new IllegalArgumentException("Unknown delivery type: " + p[0]);
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
                    Delivery delivery = createDelivery(parts);
                    double fee = delivery.calculateFee();
                    grandTotal += fee;
                    System.out.printf("%s: %.2f%n", delivery.getType(), fee);
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
