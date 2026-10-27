import java.util.Locale;
import java.util.Scanner;

/** Late fines: abstract LibraryItem mandates a fine calculation in each item type. */
public class LibraryLateFineCounter {

    abstract static class LibraryItem {
        protected final String title;
        protected final int daysLate;
        LibraryItem(String title, int daysLate) {
            if (daysLate < 0) throw new IllegalArgumentException("daysLate cannot be negative");
            this.title = title;
            this.daysLate = daysLate;
        }
        String getTitle() { return title; }
        abstract double calculateFine();
    }

    static class Book extends LibraryItem {
        Book(String t, int d) { super(t, d); }
        double calculateFine() { return 2.0 * daysLate; }
    }

    static class Dvd extends LibraryItem {
        private static final double MAX_FINE = 50.0;
        Dvd(String t, int d) { super(t, d); }
        double calculateFine() { return Math.min(5.0 * daysLate, MAX_FINE); }
    }

    static class Magazine extends LibraryItem {
        Magazine(String t, int d) { super(t, d); }
        double calculateFine() { return 1.0 * daysLate; }
    }

    static LibraryItem createItem(String[] p) {
        int daysLate = Integer.parseInt(p[2]);
        switch (p[0].toUpperCase()) {
            case "BOOK": return new Book(p[1], daysLate);
            case "DVD": return new Dvd(p[1], daysLate);
            case "MAGAZINE": return new Magazine(p[1], daysLate);
            default: throw new IllegalArgumentException("Unknown item type: " + p[0]);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double totalFines = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    LibraryItem item = createItem(parts);
                    double fine = item.calculateFine();
                    totalFines += fine;
                    System.out.printf("%s: %.2f%n", item.getTitle(), fine);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total Fines: %.2f%n", totalFines);
    }
}
