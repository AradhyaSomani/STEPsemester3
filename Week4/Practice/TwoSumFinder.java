import java.util.Arrays;

/** Finds two indices whose values add up to a target (brute force, nested loops). */
public class TwoSumFinder {

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No valid pair found for target " + target);
    }

    public static void main(String[] args) {
        int[][] inputs = {{2, 7, 11, 15}, {3, 2, 4}, {1, 2, 3}};
        int[] targets = {9, 6, 100};
        for (int i = 0; i < inputs.length; i++) {
            try {
                System.out.println("nums = " + Arrays.toString(inputs[i]) + ", target = " + targets[i]
                        + " -> " + Arrays.toString(twoSum(inputs[i], targets[i])));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
