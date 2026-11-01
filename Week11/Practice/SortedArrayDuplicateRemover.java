import java.util.Arrays;

/**
 * Two pointers, in place: Time O(n), extra Space O(1).
 * It is in-place because the unique values are written back into the same array;
 * only two integer indices are used and no second array is created.
 */
public class SortedArrayDuplicateRemover {

    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        if (nums.length == 0) return 0;
        int uniqueCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueCount - 1]) {
                nums[uniqueCount++] = nums[i];
            }
        }
        return uniqueCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 3, 4, 4, 5};
        int length = removeDuplicates(nums);
        System.out.println("length = " + length);
        System.out.println("nums[0.." + (length - 1) + "] = " + Arrays.toString(Arrays.copyOf(nums, length)));
    }
}
