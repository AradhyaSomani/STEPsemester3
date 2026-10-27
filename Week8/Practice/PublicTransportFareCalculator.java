import java.util.Locale;
import java.util.Scanner;

/** Calculates fares per transport type through polymorphism. */
public class PublicTransportFareCalculator {

    abstract static class Journey {
        protected final double distance;
        Journey(double distance) {
            if (distance <= 0) throw new IllegalArgumentException("Distance must be positive");
            this.distance = distance;
        }
        abstract String getType();
        abstract double calculateFare();
    }

    static class BusJourney extends Journey {
        private static final double MAX_FARE = 10.0;
        BusJourney(double d) { super(d); }
        String getType() { return "BUS"; }
        double calculateFare() { return Math.min(2.0 + 0.10 * distance, MAX_FARE); }
    }

    static class TrainJourney extends Journey {
        TrainJourney(double d) { super(d); }
        String getType() { return "TRAIN"; }
        double calculateFare() { return 3.0 + 0.15 * distance; }
    }

    static class MetroJourney extends Journey {
        private final double peakHourFactor;
        MetroJourney(double d, double peakHourFactor) {
            super(d);
            if (peakHourFactor < 1.0 || peakHourFactor > 2.0) {
                throw new IllegalArgumentException("PeakHourFactor must be between 1.0 and 2.0");
            }
            this.peakHourFactor = peakHourFactor;
        }
        String getType() { return "METRO"; }
        double calculateFare() { return (1.5 + 0.20 * distance) * peakHourFactor; }
    }

    static Journey createJourney(String[] p) {
        double distance = Double.parseDouble(p[1]);
        switch (p[0].toUpperCase()) {
            case "BUS": return new BusJourney(distance);
            case "TRAIN": return new TrainJourney(distance);
            case "METRO": return new MetroJourney(distance, Double.parseDouble(p[2]));
            default: throw new IllegalArgumentException("Unknown transport type: " + p[0]);
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
                    Journey journey = createJourney(parts);
                    double fare = journey.calculateFare();
                    total += fare;
                    System.out.printf("%s: %.2f%n", journey.getType(), fare);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "' (" + e.getMessage() + ")");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}
