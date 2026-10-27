import java.util.Arrays;

/** Product of all elements except self, without division (prefix and suffix passes). */
public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements.");
        }
        int[] answer = new int[nums.length];
        int leftProduct = 1;
        for (int i = 0; i < nums.length; i++) {          // forward pass
            answer[i] = leftProduct;
            leftProduct *= nums[i];
        }
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {     // backward pass
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] inputs = {{1, 2, 3, 4}, {-1, 1, 0, -3, 3}, {5}};
        for (int[] nums : inputs) {
            try {
                System.out.println("nums = " + Arrays.toString(nums) + " -> " + Arrays.toString(productExceptSelf(nums)));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
