import java.util.Arrays;

/** Finds the top three scores in a single pass, without sorting. */
public class TopThreePodiumFinder {

    public static int[] findTopThreeScores(int[] scores) {
        if (scores == null || scores.length < 3) {
            throw new IllegalArgumentException("At least 3 scores are required.");
        }
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        for (int score : scores) {
            if (score >= first) {
                third = second;
                second = first;
                first = score;
            } else if (score >= second) {
                third = second;
                second = score;
            } else if (score > third) {
                third = score;
            }
        }
        return new int[]{first, second, third};
    }

    public static void main(String[] args) {
        int[][] samples = {{45, 82, 79, 90, 33, 90, 61}, {10, 20}};
        for (int[] scores : samples) {
            try {
                System.out.println(Arrays.toString(scores) + " -> " + Arrays.toString(findTopThreeScores(scores)));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
