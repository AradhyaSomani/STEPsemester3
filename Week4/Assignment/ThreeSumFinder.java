import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Finds all unique triplets that sum to zero (sort + two pointers). */
public class ThreeSumFinder {

    private static final int TARGET_SUM = 0;

    public static int[][] threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Array must contain at least 3 elements.");
        }
        int[] sorted = nums.clone();                      // keep caller's array unchanged
        Arrays.sort(sorted);
        List<int[]> triplets = new ArrayList<>();

        for (int i = 0; i < sorted.length - 2; i++) {
            if (i > 0 && sorted[i] == sorted[i - 1]) continue;       // skip duplicate anchor
            int left = i + 1;
            int right = sorted.length - 1;
            while (left < right) {
                int sum = sorted[i] + sorted[left] + sorted[right];
                if (sum == TARGET_SUM) {
                    triplets.add(new int[]{sorted[i], sorted[left], sorted[right]});
                    left++;
                    right--;
                    while (left < right && sorted[left] == sorted[left - 1]) left++;
                    while (left < right && sorted[right] == sorted[right + 1]) right--;
                } else if (sum < TARGET_SUM) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] inputs = {{-1, 0, 1, 2, -1, -4}, {0, 0, 0}, {-2, 0, 0, 2, 2, -2, 0}, {1, 2}};
        for (int[] nums : inputs) {
            try {
                System.out.println("nums = " + Arrays.toString(nums) + " -> " + Arrays.deepToString(threeSum(nums)));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
