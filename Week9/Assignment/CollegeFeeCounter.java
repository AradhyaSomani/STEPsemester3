import java.util.Locale;
import java.util.Scanner;

/** College fees: the bus is an optional capability (interface) shared by some student types. */
public class CollegeFeeCounter {

    interface BusUser {
        double TRANSPORT_FEE = 12000.0;         // written in one place only
    }

    abstract static class Student {
        protected static final double BASE_TUITION = 40000.0;
        protected final String name;
        Student(String name) { this.name = name; }
        String getName() { return name; }
        abstract double calculateTuition();
        final double calculateTotalFee() {
            double fee = calculateTuition();
            if (this instanceof BusUser) {
                fee += BusUser.TRANSPORT_FEE;
            }
            return fee;
        }
    }

    static class DayScholar extends Student implements BusUser {
        DayScholar(String n) { super(n); }
        double calculateTuition() { return BASE_TUITION; }
    }

    static class Hosteller extends Student {
        private static final double HOSTEL_FEE = 60000.0;
        Hosteller(String n) { super(n); }
        double calculateTuition() { return BASE_TUITION + HOSTEL_FEE; }
    }

    static class ScholarshipStudent extends Student implements BusUser {
        ScholarshipStudent(String n) { super(n); }
        double calculateTuition() { return BASE_TUITION / 2; }
    }

    static Student createStudent(String type, String name) {
        switch (type.toUpperCase()) {
            case "DAY_SCHOLAR": return new DayScholar(name);
            case "HOSTELLER": return new Hosteller(name);
            case "SCHOLAR": return new ScholarshipStudent(name);
            default: throw new IllegalArgumentException("Unknown student type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double totalCollected = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Student student = createStudent(parts[0], parts[1]);
                    double fee = student.calculateTotalFee();
                    totalCollected += fee;
                    System.out.printf("%s: %.2f%n", student.getName(), fee);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total Collected: %.2f%n", totalCollected);
    }
}
