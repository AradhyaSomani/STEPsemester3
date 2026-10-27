import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Renewal dates: each plan type has its own validity period. */
public class StreamingPlanRenewalReminder {

    abstract static class Plan {
        protected final String subscriberName;
        protected final LocalDate startDate;
        Plan(String subscriberName, LocalDate startDate) {
            this.subscriberName = subscriberName;
            this.startDate = startDate;
        }
        String getSubscriberName() { return subscriberName; }
        abstract int getValidityDays();
        LocalDate calculateRenewalDate() { return startDate.plusDays(getValidityDays()); }
    }

    static class BasicPlan extends Plan {
        BasicPlan(String n, LocalDate d) { super(n, d); }
        int getValidityDays() { return 30; }
    }

    static class StandardPlan extends Plan {
        StandardPlan(String n, LocalDate d) { super(n, d); }
        int getValidityDays() { return 90; }
    }

    static class PremiumPlan extends Plan {
        PremiumPlan(String n, LocalDate d) { super(n, d); }
        int getValidityDays() { return 365; }
    }

    static Plan createPlan(String type, String name, LocalDate start) {
        switch (type.toUpperCase()) {
            case "BASIC": return new BasicPlan(name, start);
            case "STANDARD": return new StandardPlan(name, start);
            case "PREMIUM": return new PremiumPlan(name, start);
            default: throw new IllegalArgumentException("Unknown plan type: " + type);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Plan plan = createPlan(parts[0], parts[1], LocalDate.parse(parts[2]));
                    System.out.println(plan.getSubscriberName() + ": " + plan.calculateRenewalDate());
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
        }
    }
}
