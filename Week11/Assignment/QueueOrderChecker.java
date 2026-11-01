import java.util.Arrays;

/** Finds the first position where the queue breaks increasing order (equal tokens allowed). */
public class QueueOrderChecker {

    private static final int IN_ORDER = -1;

    public static int firstBreak(int[] tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("Tokens cannot be null.");
        }
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i] < tokens[i - 1]) {
                return i;
            }
        }
        return IN_ORDER;
    }

    public static void main(String[] args) {
        int[][] samples = {{3, 5, 5, 8, 6, 9}, {1, 2, 2, 7}, {}, {4}};
        for (int[] tokens : samples) {
            System.out.println("tokens = " + Arrays.toString(tokens) + " -> " + firstBreak(tokens));
        }
    }
}
