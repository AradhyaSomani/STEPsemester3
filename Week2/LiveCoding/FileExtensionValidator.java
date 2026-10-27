import java.util.Scanner;

/** Checks that an uploaded filename has an accepted extension. */
public class FileExtensionValidator {

    private static final String[] ACCEPTED_EXTENSIONS = {"pdf", "docx", "zip"};
    private static final String ACCEPTED_MESSAGE = "Accepted";
    private static final String REJECTED_MESSAGE = "Rejected — invalid file type";

    public static String validateFileExtension(String filename) {
        if (filename == null) {
            throw new NullPointerException("Filename cannot be null.");
        }
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return REJECTED_MESSAGE;
        }
        String extension = filename.substring(dotIndex + 1);
        for (String accepted : ACCEPTED_EXTENSIONS) {
            if (accepted.equalsIgnoreCase(extension)) {
                return ACCEPTED_MESSAGE;
            }
        }
        return REJECTED_MESSAGE;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter filename: ");
            System.out.println(validateFileExtension(scanner.nextLine().trim()));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
