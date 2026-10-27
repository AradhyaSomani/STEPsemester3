import java.util.Locale;
import java.util.Scanner;

/** Electricity bills: abstract Connection holds units and forces a bill calculation. */
public class ElectricityConnectionBilling {

    abstract static class Connection {
        protected final int units;
        Connection(int units) {
            if (units < 0) throw new IllegalArgumentException("units cannot be negative");
            this.units = units;
        }
        abstract String getType();
        abstract double calculateBill();
    }

    static class HomeConnection extends Connection {
        private static final int SLAB_LIMIT = 100;
        HomeConnection(int u) { super(u); }
        String getType() { return "HOME"; }
        double calculateBill() {
            int firstSlab = Math.min(units, SLAB_LIMIT);
            int rest = Math.max(0, units - SLAB_LIMIT);
            return firstSlab * 5.0 + rest * 7.0;
        }
    }

    static class ShopConnection extends Connection {
        private static final double FIXED_CHARGE = 100.0;
        ShopConnection(int u) { super(u); }
        String getType() { return "SHOP"; }
        double calculateBill() { return units * 8.0 + FIXED_CHARGE; }
    }

    static class FactoryConnection extends Connection {
        private static final double MINIMUM_BILL = 1000.0;
        FactoryConnection(int u) { super(u); }
        String getType() { return "FACTORY"; }
        double calculateBill() { return Math.max(units * 6.0, MINIMUM_BILL); }
    }

    static Connection createConnection(String type, int units) {
        switch (type.toUpperCase()) {
            case "HOME": return new HomeConnection(units);
            case "SHOP": return new ShopConnection(units);
            case "FACTORY": return new FactoryConnection(units);
            default: throw new IllegalArgumentException("Unknown connection type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double total = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Connection connection = createConnection(parts[0], Integer.parseInt(parts[1]));
                    double bill = connection.calculateBill();
                    total += bill;
                    System.out.printf("%s: %.2f%n", connection.getType(), bill);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}
