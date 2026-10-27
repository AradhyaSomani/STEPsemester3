import java.util.Locale;
import java.util.Scanner;

/** Parking charges: each vehicle type applies its own rule. */
public class CampusParkingChargeCalculator {

    abstract static class ParkedVehicle {
        protected final int hours;
        ParkedVehicle(int hours) {
            if (hours < 1 || hours > 24) throw new IllegalArgumentException("Hours must be between 1 and 24");
            this.hours = hours;
        }
        abstract String getType();
        abstract double calculateCharge();
    }

    static class Bike extends ParkedVehicle {
        Bike(int h) { super(h); }
        String getType() { return "BIKE"; }
        double calculateCharge() { return 10.0 * hours; }
    }

    static class Car extends ParkedVehicle {
        Car(int h) { super(h); }
        String getType() { return "CAR"; }
        double calculateCharge() { return 30.0 + 20.0 * (hours - 1); }
    }

    static class Truck extends ParkedVehicle {
        private static final double MINIMUM_CHARGE = 100.0;
        Truck(int h) { super(h); }
        String getType() { return "TRUCK"; }
        double calculateCharge() { return Math.max(50.0 * hours, MINIMUM_CHARGE); }
    }

    static ParkedVehicle createVehicle(String type, int hours) {
        switch (type.toUpperCase()) {
            case "BIKE": return new Bike(hours);
            case "CAR": return new Car(hours);
            case "TRUCK": return new Truck(hours);
            default: throw new IllegalArgumentException("Unknown vehicle type: " + type);
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
                    ParkedVehicle vehicle = createVehicle(parts[0], Integer.parseInt(parts[1]));
                    double charge = vehicle.calculateCharge();
                    grandTotal += charge;
                    System.out.printf("%s: %.2f%n", vehicle.getType(), charge);
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
