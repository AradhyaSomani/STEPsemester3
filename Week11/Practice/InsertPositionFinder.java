import java.util.Arrays;

/**
 * Binary search for the target or its insertion point.
 * Time: O(log n). Space: O(1). Scales to very large arrays because each step halves the range.
 */
public class InsertPositionFinder {

    public static int searchInsertPosition(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return low;            // the position where the target would be inserted
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int[] targets = {5, 2, 7, 0};
        for (int target : targets) {
            System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target
                    + " -> " + searchInsertPosition(nums, target));
        }
    }
}
