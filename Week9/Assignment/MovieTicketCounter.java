import java.util.Locale;
import java.util.Scanner;

/** Movie tickets: the convenience fee is written once in the abstract Ticket class. */
public class MovieTicketCounter {

    abstract static class Ticket {
        private static final double CONVENIENCE_FEE = 20.0;
        protected final int count;
        Ticket(int count) {
            if (count <= 0) throw new IllegalArgumentException("count must be positive");
            this.count = count;
        }
        abstract String getSeatType();
        abstract double getPricePerTicket();
        final double calculateAmount() { return count * (getPricePerTicket() + CONVENIENCE_FEE); }
    }

    static class RegularTicket extends Ticket {
        RegularTicket(int c) { super(c); }
        String getSeatType() { return "REGULAR"; }
        double getPricePerTicket() { return 150.0; }
    }

    static class PremiumTicket extends Ticket {
        PremiumTicket(int c) { super(c); }
        String getSeatType() { return "PREMIUM"; }
        double getPricePerTicket() { return 250.0; }
    }

    static class ReclinerTicket extends Ticket {
        ReclinerTicket(int c) { super(c); }
        String getSeatType() { return "RECLINER"; }
        double getPricePerTicket() { return 400.0; }
    }

    static Ticket createTicket(String seat, int count) {
        switch (seat.toUpperCase()) {
            case "REGULAR": return new RegularTicket(count);
            case "PREMIUM": return new PremiumTicket(count);
            case "RECLINER": return new ReclinerTicket(count);
            default: throw new IllegalArgumentException("Unknown seat type: " + seat);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double total = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int bookings = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < bookings; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Ticket ticket = createTicket(parts[0], Integer.parseInt(parts[1]));
                    double amount = ticket.calculateAmount();
                    total += amount;
                    System.out.printf("%s: %.2f%n", ticket.getSeatType(), amount);
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
