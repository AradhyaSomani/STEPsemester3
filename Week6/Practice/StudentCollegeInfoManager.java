/** Shared college name and student counter kept in static fields. */
public class StudentCollegeInfoManager {

    static class Student {
        private static final String collegeName = "SRM Institute of Science and Technology";
        private static int studentCount = 0;

        private final String name;
        private final int attendance;

        Student(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            studentCount++;
        }

        static void printCollegeInfo() {
            System.out.println(collegeName);
            System.out.println("Students created: " + studentCount);
        }
    }

    public static void main(String[] args) {
        new Student("Ravi", 90);
        new Student("Anitha", 85);
        Student.printCollegeInfo();
    }
}
