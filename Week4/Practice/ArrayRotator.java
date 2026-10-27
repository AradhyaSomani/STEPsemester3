import java.util.Arrays;

/** Rotates an array to the right by k positions using index arithmetic. */
public class ArrayRotator {

    public static int[] rotateArray(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k cannot be negative.");
        }
        if (nums.length == 0) {
            return new int[0];
        }
        int shift = k % nums.length;
        int[] rotated = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rotated[(i + shift) % nums.length] = nums[i];
        }
        return rotated;
    }

    public static void main(String[] args) {
        int[][] inputs = {{1, 2, 3, 4, 5, 6, 7}, {1, 2}};
        int[] shifts = {3, 3};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("nums = " + Arrays.toString(inputs[i]) + ", k = " + shifts[i]
                    + " -> " + Arrays.toString(rotateArray(inputs[i], shifts[i])));
        }
    }
}
