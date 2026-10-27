import java.util.Locale;
import java.util.Scanner;

/** Festival bonus: each employee type follows its own bonus rule. */
public class FestivalBonusCalculator {

    abstract static class Employee {
        protected final String name;
        protected final double monthlySalary;
        Employee(String name, double monthlySalary) {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }
        String getName() { return name; }
        abstract double calculateBonus();
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String n, double s) { super(n, s); }
        double calculateBonus() { return monthlySalary * 0.10; }
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String n, double s) { super(n, s); }
        double calculateBonus() { return monthlySalary * 0.05; }
    }

    static class Intern extends Employee {
        private static final double FIXED_BONUS = 2000.0;
        Intern(String n, double s) { super(n, s); }
        double calculateBonus() { return FIXED_BONUS; }
    }

    static Employee createEmployee(String type, String name, double salary) {
        switch (type.toUpperCase()) {
            case "FULLTIME": return new FullTimeEmployee(name, salary);
            case "PARTTIME": return new PartTimeEmployee(name, salary);
            case "INTERN": return new Intern(name, salary);
            default: throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double totalBonus = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Employee employee = createEmployee(parts[0], parts[1], Double.parseDouble(parts[2]));
                    double bonus = employee.calculateBonus();
                    totalBonus += bonus;
                    System.out.printf("%s: %.2f%n", employee.getName(), bonus);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total Bonus: %.2f%n", totalBonus);
    }
}
