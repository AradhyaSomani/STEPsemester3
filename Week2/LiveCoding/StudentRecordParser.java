import java.util.Scanner;

/** Parses a "Name,RollNumber,Department" CSV line. */
public class StudentRecordParser {

    private static final int EXPECTED_FIELDS = 3;

    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null) {
            throw new NullPointerException("CSV line cannot be null.");
        }
        String[] fields = csvLine.split(",");
        if (fields.length != EXPECTED_FIELDS) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name: " + fields[0].trim()
                + " | Roll No: " + fields[1].trim()
                + " | Dept: " + fields[2].trim());
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter CSV line (Name,RollNumber,Department): ");
            parseStudentRecord(scanner.nextLine());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
