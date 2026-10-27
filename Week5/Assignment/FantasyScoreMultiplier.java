import java.util.Arrays;

/** Applies captain (2x) and vice-captain (1.5x) multipliers directly to the score array. */
public class FantasyScoreMultiplier {

    private static final double CAPTAIN_MULTIPLIER = 2.0;
    private static final double VICE_CAPTAIN_MULTIPLIER = 1.5;

    public static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        if (playerScores == null) {
            throw new IllegalArgumentException("Scores cannot be null.");
        }
        if (captainIndex == viceCaptainIndex) {
            throw new IllegalArgumentException("Captain and vice-captain must be different players.");
        }
        try {
            playerScores[captainIndex] *= CAPTAIN_MULTIPLIER;
            playerScores[viceCaptainIndex] *= VICE_CAPTAIN_MULTIPLIER;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Captain or vice-captain index is outside the lineup.");
        }
    }

    public static void main(String[] args) {
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores));
        try {
            applyMultipliers(scores, 1, 9);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
