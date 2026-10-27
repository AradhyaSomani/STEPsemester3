import java.util.Locale;
import java.util.Scanner;

/** Weekly payroll: abstract Staff forces every staff type to define its own pay. */
public class WeeklyStaffPay {

    abstract static class Staff {
        protected final String name;
        Staff(String name) { this.name = name; }
        String getName() { return name; }
        abstract double calculatePay();
    }

    static class FullTimeStaff extends Staff {
        private final double weeklySalary;
        FullTimeStaff(String name, double weeklySalary) { super(name); this.weeklySalary = weeklySalary; }
        double calculatePay() { return weeklySalary; }
    }

    static class HourlyStaff extends Staff {
        private static final double REGULAR_HOURS = 40;
        private static final double OVERTIME_MULTIPLIER = 1.5;
        private final double hours, rate;
        HourlyStaff(String name, double hours, double rate) { super(name); this.hours = hours; this.rate = rate; }
        double calculatePay() {
            double regular = Math.min(hours, REGULAR_HOURS);
            double overtime = Math.max(0, hours - REGULAR_HOURS);
            return regular * rate + overtime * rate * OVERTIME_MULTIPLIER;
        }
    }

    static class InternStaff extends Staff {
        private final double stipend;
        InternStaff(String name, double stipend) { super(name); this.stipend = stipend; }
        double calculatePay() { return stipend; }
    }

    static Staff createStaff(String[] p) {
        switch (p[0].toUpperCase()) {
            case "FULLTIME": return new FullTimeStaff(p[1], Double.parseDouble(p[2]));
            case "HOURLY": return new HourlyStaff(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
            case "INTERN": return new InternStaff(p[1], Double.parseDouble(p[2]));
            default: throw new IllegalArgumentException("Unknown staff type: " + p[0]);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double totalPayroll = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Staff staff = createStaff(parts);
                    double pay = staff.calculatePay();
                    totalPayroll += pay;
                    System.out.printf("%s: %.2f%n", staff.getName(), pay);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total Payroll: %.2f%n", totalPayroll);
    }
}
