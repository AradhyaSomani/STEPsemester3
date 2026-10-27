/** Attendance sheet that reveals only a count and a yes/no lookup. */
public class AttendanceSheet {

    private final String[] presentStudents;
    private int presentCount;

    public AttendanceSheet(int maxClassSize) {
        if (maxClassSize <= 0) {
            throw new IllegalArgumentException("Class size must be positive.");
        }
        this.presentStudents = new String[maxClassSize];
    }

    public void markPresent(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (isPresent(name)) {
            return;                         // no duplicates
        }
        if (presentCount == presentStudents.length) {
            System.out.println("Sheet full: cannot mark " + name);
            return;
        }
        presentStudents[presentCount++] = name;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public boolean isPresent(String name) {
        for (int i = 0; i < presentCount; i++) {
            if (presentStudents[i].equals(name)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana");
        System.out.println("getPresentCount() -> " + sheet.getPresentCount());
        System.out.println("isPresent(\"Ben\") -> " + sheet.isPresent("Ben"));
        System.out.println("isPresent(\"Chen\") -> " + sheet.isPresent("Chen"));
    }
}
