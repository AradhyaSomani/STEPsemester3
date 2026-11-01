import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Approach 1 - brute force (all pairs): Time O(n^2), Space O(1).
 * Approach 2 - HashSet of complements: Time O(n), Space O(n).
 * Approach 3 - sort + two pointers: Time O(n log n), Space O(1) extra on a copy-free sort.
 */
public class PairWithTargetSum {

    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        requireArray(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return true;
            }
        }
        return false;
    }

    public static boolean hasPairWithSum(int[] nums, int target) {
        requireArray(nums);
        Set<Integer> seen = new HashSet<>();
        for (int value : nums) {
            if (seen.contains(target - value)) return true;     // complement came from an earlier position
            seen.add(value);
        }
        return false;
    }

    public static boolean hasPairWithSumTwoPointers(int[] nums, int target) {
        requireArray(nums);
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int left = 0, right = sorted.length - 1;
        while (left < right) {
            long sum = (long) sorted[left] + sorted[right];
            if (sum == target) return true;
            if (sum < target) left++;
            else right--;
        }
        return false;
    }

    private static void requireArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
    }

    public static void main(String[] args) {
        int[] first = {2, 7, 11, 15};
        int[] second = {3, 4, 6};
        System.out.println("nums = " + Arrays.toString(first) + ", target = 9 -> " + hasPairWithSum(first, 9));
        System.out.println("nums = " + Arrays.toString(second) + ", target = 20 -> " + hasPairWithSum(second, 20));
        System.out.println("Brute force: " + hasPairWithSumBruteForce(first, 9) + ", " + hasPairWithSumBruteForce(second, 20));
        System.out.println("Two pointers: " + hasPairWithSumTwoPointers(first, 9) + ", " + hasPairWithSumTwoPointers(second, 20));
        System.out.println("Duplicates and negatives: " + hasPairWithSum(new int[]{-3, 5, 5, 8}, 10));
    }
}
