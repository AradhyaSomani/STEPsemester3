/**
 * Binary search over a catalog sorted by ISBN.
 * Time: O(log n) per query. Space: O(1).
 * Suits a catalog that is queried far more often than it is updated, because the
 * sorted order is paid for once and every query then needs only about log2(n) steps.
 */
public class LibraryCatalogLookup {

    private static final String NOT_FOUND = "Not Found";

    static class BookRecord {
        private final String isbn;
        private final String title;

        BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        String getIsbn() { return isbn; }
        String getTitle() { return title; }
    }

    public static String findBook(BookRecord[] catalog, String targetIsbn) {
        if (catalog == null || targetIsbn == null) {
            throw new IllegalArgumentException("Catalog and ISBN cannot be null.");
        }
        int low = 0;
        int high = catalog.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = catalog[mid].getIsbn().compareTo(targetIsbn);
            if (comparison == 0) return catalog[mid].getTitle();
            if (comparison < 0) low = mid + 1;
            else high = mid - 1;
        }
        return NOT_FOUND;
    }

    public static void main(String[] args) {
        BookRecord[] catalog = {
            new BookRecord("0001112223", "Introduction to Algebra"),
            new BookRecord("0002223334", "Beginning Python"),
            new BookRecord("0003334445", "Classic Mythology"),
            new BookRecord("0004445556", "Data and Society"),
            new BookRecord("0005556667", "European History")
        };
        System.out.println("\"" + findBook(catalog, "0003334445") + "\"");
        System.out.println("\"" + findBook(catalog, "0009998887") + "\"");
    }
}
