import java.util.Arrays;

/**
 * Finds the row with the largest total (smallest index on ties).
 * Time: O(r * c). Space: O(1) extra.
 */
public class BusiestBusRowFinder {

    /** Returns {rowIndex, total}. */
    public static int[] busiestRow(int[][] grid) {
        if (grid == null || grid.length == 0) {
            throw new IllegalArgumentException("Grid cannot be empty.");
        }
        int bestRow = 0;
        int bestTotal = -1;
        for (int row = 0; row < grid.length; row++) {
            int rowTotal = 0;
            for (int seat : grid[row]) {
                rowTotal += seat;
            }
            if (rowTotal > bestTotal) {              // strictly larger keeps smaller index on ties
                bestTotal = rowTotal;
                bestRow = row;
            }
        }
        return new int[]{bestRow, bestTotal};
    }

    public static void main(String[] args) {
        int[][][] samples = {
            {{2, 0, 1}, {3, 3, 1}, {1, 1, 1}},
            {{1, 1}, {2, 0}}
        };
        for (int[][] grid : samples) {
            int[] result = busiestRow(grid);
            System.out.println("grid = " + Arrays.deepToString(grid)
                    + " -> Row " + result[0] + ", Total " + result[1]);
        }
        try {
            busiestRow(new int[0][]);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
