import java.util.Locale;
import java.util.Scanner;

/** Hostel electricity bills: each room type has its own rule. */
public class HostelElectricityBill {

    abstract static class Room {
        protected final int units;
        Room(int units) {
            if (units <= 0) throw new IllegalArgumentException("Units must be positive");
            this.units = units;
        }
        abstract String getType();
        abstract double calculateBill();
    }

    static class SingleRoom extends Room {
        SingleRoom(int u) { super(u); }
        String getType() { return "SINGLE"; }
        double calculateBill() { return 8.0 * units; }
    }

    static class SharedRoom extends Room {
        private final int occupants;        // extra value stored inside the subclass
        SharedRoom(int u, int occupants) {
            super(u);
            if (occupants < 2 || occupants > 4) throw new IllegalArgumentException("Occupants must be 2 to 4");
            this.occupants = occupants;
        }
        String getType() { return "SHARED"; }
        double calculateBill() { return 6.0 * units / occupants; }
    }

    static class AcRoom extends Room {
        private static final double FIXED_CHARGE = 200.0;
        AcRoom(int u) { super(u); }
        String getType() { return "AC"; }
        double calculateBill() { return 10.0 * units + FIXED_CHARGE; }
    }

    static Room createRoom(String[] p) {
        int units = Integer.parseInt(p[1]);
        switch (p[0].toUpperCase()) {
            case "SINGLE": return new SingleRoom(units);
            case "SHARED": return new SharedRoom(units, Integer.parseInt(p[2]));
            case "AC": return new AcRoom(units);
            default: throw new IllegalArgumentException("Unknown room type: " + p[0]);
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
                    Room room = createRoom(parts);
                    double bill = room.calculateBill();
                    grandTotal += bill;
                    System.out.printf("%s: %.2f%n", room.getType(), bill);
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
