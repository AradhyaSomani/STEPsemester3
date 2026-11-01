/**
 * Total items and position of the fullest bin in an m x n grid.
 * Time: O(m * n) - every cell is visited once. Extra space: O(1).
 */
public class WarehouseBinGridScan {

    /** Returns {total, maxRow, maxCol}; ties keep the first bin in row-major order. */
    public static int[] warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must not be empty.");
        }
        int total = 0;
        int maxValue = -1;
        int maxRow = 0, maxCol = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                total += grid[row][col];
                if (grid[row][col] > maxValue) {          // strictly greater keeps the first tie
                    maxValue = grid[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        return new int[]{total, maxRow, maxCol};
    }

    public static void main(String[] args) {
        int[][] grid = {{4, 9, 2}, {7, 1, 6}, {3, 12, 5}};
        int[] summary = warehouseSummary(grid);
        System.out.println("total = " + summary[0] + ", maxCoordinate = (" + summary[1] + ", " + summary[2] + ")");
        try {
            warehouseSummary(new int[0][0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
