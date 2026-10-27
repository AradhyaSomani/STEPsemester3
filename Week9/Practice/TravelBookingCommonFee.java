import java.util.Locale;
import java.util.Scanner;

/** Travel bookings: the common booking fee lives only in the abstract base class. */
public class TravelBookingCommonFee {

    abstract static class Booking {
        private static final double BOOKING_FEE = 50.0;      // the one place to change the fee
        protected final double distanceKm;
        Booking(double distanceKm) {
            if (distanceKm <= 0) throw new IllegalArgumentException("distance must be positive");
            this.distanceKm = distanceKm;
        }
        abstract String getMode();
        abstract double calculateBaseFare();
        final double calculateTotal() { return calculateBaseFare() + BOOKING_FEE; }
    }

    static class BusBooking extends Booking {
        BusBooking(double d) { super(d); }
        String getMode() { return "BUS"; }
        double calculateBaseFare() { return 2.0 * distanceKm; }
    }

    static class TrainBooking extends Booking {
        TrainBooking(double d) { super(d); }
        String getMode() { return "TRAIN"; }
        double calculateBaseFare() { return 1.5 * distanceKm; }
    }

    static class FlightBooking extends Booking {
        FlightBooking(double d) { super(d); }
        String getMode() { return "FLIGHT"; }
        double calculateBaseFare() { return 2500.0 + 4.0 * distanceKm; }
    }

    static Booking createBooking(String mode, double distance) {
        switch (mode.toUpperCase()) {
            case "BUS": return new BusBooking(distance);
            case "TRAIN": return new TrainBooking(distance);
            case "FLIGHT": return new FlightBooking(distance);
            default: throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Booking booking = createBooking(parts[0], Double.parseDouble(parts[1]));
                    System.out.printf("%s: %.2f%n", booking.getMode(), booking.calculateTotal());
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
        }
    }
}
