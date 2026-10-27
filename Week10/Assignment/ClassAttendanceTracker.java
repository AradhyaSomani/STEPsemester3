import java.util.Arrays;

/**
 * Attendance summary in a single pass.
 * Time: O(n). Space: O(1) extra.
 */
public class ClassAttendanceTracker {

    /** Returns {presentDays, longestStreak}. */
    public static int[] attendanceSummary(int[] days) {
        if (days == null || days.length == 0) {
            throw new IllegalArgumentException("Attendance array cannot be empty.");
        }
        int presentDays = 0;
        int currentStreak = 0;
        int longestStreak = 0;
        for (int day : days) {
            if (day == 1) {
                presentDays++;
                currentStreak++;
                longestStreak = Math.max(longestStreak, currentStreak);
            } else if (day == 0) {
                currentStreak = 0;
            } else {
                throw new IllegalArgumentException("Each value must be 0 or 1, found: " + day);
            }
        }
        return new int[]{presentDays, longestStreak};
    }

    public static void main(String[] args) {
        int[][] samples = {{1, 1, 0, 1, 1, 1, 0, 1}, {0, 0, 0}, {1, 2}};
        for (int[] days : samples) {
            try {
                int[] summary = attendanceSummary(days);
                System.out.println("days = " + Arrays.toString(days)
                        + " -> Present: " + summary[0] + ", Longest streak: " + summary[1]);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
