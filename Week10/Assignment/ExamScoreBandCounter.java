import java.util.Arrays;

/**
 * Counts scores within [low, high] in a sorted array using two binary searches.
 * Linear scan: O(n) per query. This solution: O(log n) per query, O(1) space.
 */
public class ExamScoreBandCounter {

    /** First index whose value is >= target (array length if none). */
    private static int findFirstAtLeast(int[] scores, int target) {
        int low = 0, high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    /** First index whose value is > target (array length if none). */
    private static int findFirstGreaterThan(int[] scores, int target) {
        int low = 0, high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] > target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("Scores cannot be empty.");
        }
        if (low > high) {
            throw new IllegalArgumentException("low cannot be greater than high.");
        }
        return findFirstGreaterThan(scores, high) - findFirstAtLeast(scores, low);
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        int[][] bands = {{42, 58}, {90, 100}, {0, 100}, {58, 58}, {60, 40}};
        for (int[] band : bands) {
            try {
                System.out.println("low = " + band[0] + ", high = " + band[1]
                        + " -> " + countInBand(scores, band[0], band[1]));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
