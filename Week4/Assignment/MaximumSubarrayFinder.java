import java.util.Arrays;

/** Kadane's algorithm: largest sum of a contiguous subarray. */
public class MaximumSubarrayFinder {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must contain at least one element.");
        }
        int currentSum = nums[0];
        int bestSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);   // restart or extend
            bestSum = Math.max(bestSum, currentSum);
        }
        return bestSum;
    }

    public static void main(String[] args) {
        int[][] inputs = {{-2, 1, -3, 4, -1, 2, 1, -5, 4}, {-3, -1, -2}, {}};
        for (int[] nums : inputs) {
            try {
                System.out.println("nums = " + Arrays.toString(nums) + " -> " + maxSubArray(nums));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
