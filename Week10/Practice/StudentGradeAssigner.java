import java.util.Scanner;

/** Assigns a grade from marks out of 100. */
public class StudentGradeAssigner {

    private static final int MAX_MARKS = 100;

    public static char assignGrade(int marks) {
        if (marks < 0 || marks > MAX_MARKS) {
            throw new IllegalArgumentException("Marks must be between 0 and 100: " + marks);
        }
        if (marks >= 90) return 'A';
        else if (marks >= 75) return 'B';
        else if (marks >= 60) return 'C';
        else if (marks >= 40) return 'D';
        else return 'F';
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                try {
                    System.out.println("Grade " + assignGrade(Integer.parseInt(token)));
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + token + "' is not a valid number.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
