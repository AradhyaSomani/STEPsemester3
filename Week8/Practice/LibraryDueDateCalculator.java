import java.time.LocalDate;
import java.util.Scanner;

/** Computes due dates for different library item types through polymorphism. */
public class LibraryDueDateCalculator {

    private static final LocalDate CURRENT_DATE = LocalDate.of(2023, 10, 26);

    abstract static class LibraryItem {
        protected final String title;
        LibraryItem(String title) { this.title = title; }
        abstract int getBorrowingDays();
        LocalDate getDueDate(LocalDate borrowedOn) { return borrowedOn.plusDays(getBorrowingDays()); }
        String getTitle() { return title; }
    }

    static class Book extends LibraryItem {
        Book(String title) { super(title); }
        int getBorrowingDays() { return 14; }
    }

    static class Dvd extends LibraryItem {
        Dvd(String title) { super(title); }
        int getBorrowingDays() { return 7; }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title) { super(title); }
        int getBorrowingDays() { return 3; }
    }

    static LibraryItem createItem(String type, String title) {
        switch (type.toUpperCase()) {
            case "BOOK": return new Book(title);
            case "DVD": return new Dvd(title);
            case "MAGAZINE": return new Magazine(title);
            default: throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine().trim();
                try {
                    int firstSpace = line.indexOf(' ');
                    String type = line.substring(0, firstSpace);
                    String title = line.substring(firstSpace + 1).trim().replace("\"", "");
                    LibraryItem item = createItem(type, title);
                    System.out.println(item.getTitle() + ": " + item.getDueDate(CURRENT_DATE));
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + line + "' (" + e.getMessage() + ")");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
        }
    }
}
