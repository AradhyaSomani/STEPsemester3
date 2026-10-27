import java.util.Arrays;

/** Adds a flat bonus to every score directly inside the caller's array. */
public class HackathonScoreCurveBooster {

    public static void curveScores(int[] scores, int bonus) {
        if (scores == null) {
            throw new IllegalArgumentException("Scores cannot be null.");
        }
        if (bonus < 0) {
            throw new IllegalArgumentException("Bonus cannot be negative.");
        }
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    public static void main(String[] args) {
        int[] scores = {70, 85, 60};
        System.out.println("Before: " + Arrays.toString(scores));
        curveScores(scores, 10);
        System.out.println("After:  " + Arrays.toString(scores));
        try {
            curveScores(scores, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
