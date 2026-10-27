/** Flags duplicate seat numbers using arrays and nested loops only. */
public class ExamSeatDuplicateChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null) {
            throw new NullPointerException("Seat array cannot be null.");
        }
        boolean duplicateFound = false;
        int[] alreadyReported = new int[seatNumbers.length];
        int reportedCount = 0;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j] && !isReported(alreadyReported, reportedCount, seatNumbers[i])) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    alreadyReported[reportedCount++] = seatNumbers[i];
                    duplicateFound = true;
                }
            }
        }
        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    private static boolean isReported(int[] reported, int count, int seat) {
        for (int i = 0; i < count; i++) {
            if (reported[i] == seat) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] testHalls = {{101, 102, 103, 102, 105}, {101, 102, 103, 104, 105}};
        for (int[] hall : testHalls) {
            try {
                checkDuplicateSeats(hall);
            } catch (NullPointerException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
