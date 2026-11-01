import java.util.Arrays;

/**
 * Bubble sort that prints the line after every sweep. Each sweep ignores the already-placed tail,
 * so at most n - 1 sweeps happen, and it stops early after a sweep with no swap.
 */
public class HeightLineUp {

    public static void lineUp(int[] heights) {
        if (heights == null) {
            throw new IllegalArgumentException("Heights cannot be null.");
        }
        boolean swapped = true;
        for (int pass = 1; pass < heights.length && swapped; pass++) {
            swapped = false;
            for (int j = 0; j < heights.length - pass; j++) {
                if (heights[j] > heights[j + 1]) {
                    int temp = heights[j];
                    heights[j] = heights[j + 1];
                    heights[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + pass + ": " + Arrays.toString(heights));
        }
    }

    public static void main(String[] args) {
        int[][] samples = {{4, 2, 5, 1}, {1, 3, 2, 4}, {9, 7, 5, 3, 1}};
        for (int[] heights : samples) {
            System.out.println("heights = " + Arrays.toString(heights));
            lineUp(heights);
        }
    }
}
