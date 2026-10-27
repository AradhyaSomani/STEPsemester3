/** Generates result cards: average, grade and upper-case name for each student. */
public class StudentResultCardGenerator {

    static class Student {
        private final String name;
        private final int[] marks;

        Student(String name, int[] marks) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be empty.");
            }
            if (marks == null || marks.length == 0) {
                throw new IllegalArgumentException("Marks cannot be empty.");
            }
            this.name = name;
            this.marks = marks.clone();
        }

        double calculateAverage() {
            int total = 0;
            for (int mark : marks) total += mark;
            return (double) total / marks.length;
        }

        char assignGrade() {
            double average = calculateAverage();
            if (average >= 90) return 'A';
            else if (average >= 75) return 'B';
            else if (average >= 60) return 'C';
            else if (average >= 40) return 'D';
            else return 'F';
        }

        String buildResultCard() {
            return String.format("%s: Average %.1f, Grade %c", name.toUpperCase(), calculateAverage(), assignGrade());
        }
    }

    public static void main(String[] args) {
        try {
            Student[] students = {
                new Student("Asha", new int[]{80, 90, 70}),
                new Student("Ravi", new int[]{60, 55, 50})
            };
            for (Student student : students) {
                System.out.println(student.buildResultCard());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
