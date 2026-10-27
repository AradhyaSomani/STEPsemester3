import java.util.Locale;
import java.util.Scanner;

/** Cab fares: minimum fare is shared (abstract class); night service is an optional interface. */
public class CityCabFareMeter {

    interface NightService {
        double NIGHT_SURCHARGE = 0.20;
    }

    abstract static class Cab {
        private static final double MINIMUM_FARE = 100.0;
        abstract String getType();
        abstract double getRatePerKm();
        final double calculateDayFare(double km) { return Math.max(km * getRatePerKm(), MINIMUM_FARE); }
        final boolean offersNightService() { return this instanceof NightService; }
        final double calculateNightFare(double km) {
            if (!offersNightService()) {
                throw new UnsupportedOperationException("night service not available");
            }
            return calculateDayFare(km) * (1 + NightService.NIGHT_SURCHARGE);
        }
    }

    static class MiniCab extends Cab {
        String getType() { return "MINI"; }
        double getRatePerKm() { return 10.0; }
    }

    static class SedanCab extends Cab implements NightService {
        String getType() { return "SEDAN"; }
        double getRatePerKm() { return 14.0; }
    }

    static class SuvCab extends Cab implements NightService {
        String getType() { return "SUV"; }
        double getRatePerKm() { return 18.0; }
    }

    static Cab createCab(String type) {
        switch (type.toUpperCase()) {
            case "MINI": return new MiniCab();
            case "SEDAN": return new SedanCab();
            case "SUV": return new SuvCab();
            default: throw new IllegalArgumentException("Unknown cab type: " + type);
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
                    Cab cab = createCab(parts[0]);
                    double km = Double.parseDouble(parts[1]);
                    boolean night = parts[2].equalsIgnoreCase("NIGHT");
                    double fare = night ? cab.calculateNightFare(km) : cab.calculateDayFare(km);
                    total += fare;
                    System.out.printf("%s: %.2f%n", cab.getType(), fare);
                } catch (UnsupportedOperationException e) {
                    System.out.println(parts[0].toUpperCase() + ": " + e.getMessage());
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
