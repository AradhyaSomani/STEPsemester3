import java.util.Arrays;

/** Checks whether any value appears at two different positions. */
public class DuplicateChecker {

    public static boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] inputs = {{1, 2, 3, 1}, {1, 2, 3, 4}};
        for (int[] nums : inputs) {
            System.out.println("nums = " + Arrays.toString(nums) + " -> " + containsDuplicate(nums));
        }
        try {
            containsDuplicate(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
