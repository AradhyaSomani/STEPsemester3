import java.util.ArrayList;
import java.util.List;

/**
 * Spiral traversal with four shrinking boundaries.
 * Time: O(m * n). Output space: O(m * n); extra space beyond the output is O(1).
 */
public class SpiralOrderTraversal {

    public static List<Integer> spiralOrder(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null.");
        }
        List<Integer> result = new ArrayList<>();
        if (grid.length == 0 || grid[0].length == 0) return result;
        int top = 0, bottom = grid.length - 1;
        int left = 0, right = grid[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) result.add(grid[top][col]);
            top++;
            for (int row = top; row <= bottom; row++) result.add(grid[row][right]);
            right--;
            if (top <= bottom) {
                for (int col = right; col >= left; col--) result.add(grid[bottom][col]);
                bottom--;
            }
            if (left <= right) {
                for (int row = bottom; row >= top; row--) result.add(grid[row][left]);
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}}));
        System.out.println(spiralOrder(new int[][]{{1}, {2}, {3}}));
    }
}
