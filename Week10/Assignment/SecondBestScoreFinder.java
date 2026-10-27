import java.util.Arrays;

/**
 * Second-highest distinct score without sorting.
 * Time: O(n) single pass. Space: O(1). Sorting would cost O(n log n).
 */
public class SecondBestScoreFinder {

    private static final int NOT_FOUND = -1;

    public static int secondHighest(int[] scores) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("Scores cannot be empty.");
        }
        int highest = NOT_FOUND;
        int second = NOT_FOUND;
        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score < highest && score > second) {
                second = score;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        int[][] samples = {{45, 78, 92, 78, 60}, {50, 50, 50}, {}};
        for (int[] scores : samples) {
            try {
                System.out.println("scores = " + Arrays.toString(scores) + " -> " + secondHighest(scores));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
