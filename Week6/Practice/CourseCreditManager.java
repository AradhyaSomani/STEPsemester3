/** Courses with optional lab credits using constructor chaining. */
public class CourseCreditManager {

    static class Course {
        private final String code;
        private final String title;
        private final int credits;
        private final int labCredits;

        public Course(String code, String title, int credits, int labCredits) {
            this.code = code;
            this.title = title;
            this.credits = credits;
            this.labCredits = labCredits;
        }

        public Course(String code, String title, int credits) {
            this(code, title, credits, 0);        // theory-only course
        }

        public String getCode() { return code; }
        public String getTitle() { return title; }

        public int totalCredits() {
            return credits + labCredits;
        }
    }

    public static void main(String[] args) {
        Course theory = new Course("21CSC201J", "Data Structures", 4);
        Course withLab = new Course("21CSC205L", "DSA Lab", 3, 1);
        System.out.println(theory.getCode() + " total credits: " + theory.totalCredits());
        System.out.println(withLab.getCode() + " total credits: " + withLab.totalCredits());
    }
}
