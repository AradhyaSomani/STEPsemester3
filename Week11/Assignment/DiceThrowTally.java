import java.util.Arrays;

/** Counting sort for dice throws (values 1 to 6): no two throws are ever compared. */
public class DiceThrowTally {

    private static final int FACES = 6;

    public static int[] diceSort(int[] rolls) {
        if (rolls == null) {
            throw new IllegalArgumentException("Rolls cannot be null.");
        }
        int[] count = new int[FACES + 1];             // index = die face, index 0 unused
        for (int roll : rolls) {
            if (roll < 1 || roll > FACES) {
                throw new IllegalArgumentException("A die shows 1 to 6, found: " + roll);
            }
            count[roll]++;
        }
        StringBuilder tally = new StringBuilder("Counts:");
        for (int face = 1; face <= FACES; face++) {
            tally.append(' ').append(face).append(':').append(count[face]);
        }
        System.out.println(tally);

        int[] sorted = new int[rolls.length];
        int position = 0;
        for (int face = 1; face <= FACES; face++) {
            for (int times = 0; times < count[face]; times++) {
                sorted[position++] = face;
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[][] samples = {{4, 1, 6, 4, 2, 1, 4}, {6, 6, 5, 1}, {3, 9}};
        for (int[] rolls : samples) {
            try {
                System.out.println("rolls = " + Arrays.toString(rolls));
                System.out.println("Sorted: " + Arrays.toString(diceSort(rolls)));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
