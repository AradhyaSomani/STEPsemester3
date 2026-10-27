import java.util.Scanner;

/** Parses a "ProductName,SKU,Quantity" CSV line. */
public class InventoryRecordParser {

    private static final int EXPECTED_FIELDS = 3;

    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            throw new NullPointerException("CSV line cannot be null.");
        }
        String[] fields = csvLine.split(",");
        if (fields.length != EXPECTED_FIELDS) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Product: " + fields[0].trim()
                + " | SKU: " + fields[1].trim()
                + " | Qty: " + fields[2].trim());
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter CSV line (ProductName,SKU,Quantity): ");
            parseInventoryRecord(scanner.nextLine());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
