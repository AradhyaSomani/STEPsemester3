import java.util.Arrays;

/**
 * Two pointers on a sorted array: Time O(n), Space O(1).
 * A solution that ignores the ordering (all pairs) is O(n^2); a hash set is O(n) time but O(n) space.
 */
public class SortedPairSumFinder {

    /** Returns the matching pair, or null when no pair exists. */
    public static int[] pairSumSorted(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            long sum = (long) nums[left] + nums[right];
            if (sum == target) return new int[]{nums[left], nums[right]};
            if (sum < target) left++;
            else right--;
        }
        return null;
    }

    private static String describe(int[] pair) {
        return pair == null ? "Not Found" : "(" + pair[0] + ", " + pair[1] + ")";
    }

    public static void main(String[] args) {
        System.out.println(describe(pairSumSorted(new int[]{-4, -1, 0, 3, 5, 9}, 4)));
        System.out.println(describe(pairSumSorted(new int[]{1, 2, 3}, 100)));
    }
}
