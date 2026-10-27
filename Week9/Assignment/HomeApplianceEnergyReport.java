import java.util.Locale;
import java.util.Scanner;

/** Appliances: units come from the abstract base class; saver mode is an optional interface. */
public class HomeApplianceEnergyReport {

    interface SaverMode {
        double SAVER_REDUCTION = 0.25;
    }

    abstract static class Appliance {
        private static final double COST_PER_UNIT = 8.0;
        protected final double hours;
        Appliance(double hours) {
            if (hours < 0) throw new IllegalArgumentException("hours cannot be negative");
            this.hours = hours;
        }
        abstract String getName();
        abstract double getPowerWatts();
        final double calculateUnits() { return getPowerWatts() * hours / 1000.0; }
        final double calculateSaverUnits() {
            if (!(this instanceof SaverMode)) {
                throw new UnsupportedOperationException("saver mode not supported");
            }
            return calculateUnits() * (1 - SaverMode.SAVER_REDUCTION);
        }
        static double costOf(double units) { return units * COST_PER_UNIT; }
    }

    static class Fridge extends Appliance {
        Fridge(double h) { super(h); }
        String getName() { return "FRIDGE"; }
        double getPowerWatts() { return 150; }
    }

    static class AirConditioner extends Appliance implements SaverMode {
        AirConditioner(double h) { super(h); }
        String getName() { return "AC"; }
        double getPowerWatts() { return 1500; }
    }

    static class Television extends Appliance {
        Television(double h) { super(h); }
        String getName() { return "TV"; }
        double getPowerWatts() { return 100; }
    }

    static class WashingMachine extends Appliance implements SaverMode {
        WashingMachine(double h) { super(h); }
        String getName() { return "WASHER"; }
        double getPowerWatts() { return 500; }
    }

    static Appliance createAppliance(String type, double hours) {
        switch (type.toUpperCase()) {
            case "FRIDGE": return new Fridge(hours);
            case "AC": return new AirConditioner(hours);
            case "TV": return new Television(hours);
            case "WASHER": return new WashingMachine(hours);
            default: throw new IllegalArgumentException("Unknown appliance: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double totalCost = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Appliance appliance = createAppliance(parts[0], Double.parseDouble(parts[1]));
                    boolean saver = parts.length > 2 && parts[2].equalsIgnoreCase("SAVER");
                    double units = saver ? appliance.calculateSaverUnits() : appliance.calculateUnits();
                    double cost = Appliance.costOf(units);
                    totalCost += cost;
                    System.out.printf("%s: Units=%.2f Cost=%.2f%n", appliance.getName(), units, cost);
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
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }
}
