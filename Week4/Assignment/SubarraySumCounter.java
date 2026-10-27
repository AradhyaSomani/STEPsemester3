import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** Counts contiguous subarrays summing to k using prefix sums and a hash map. */
public class SubarraySumCounter {

    public static int subarraySum(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        Map<Integer, Integer> prefixFrequency = new HashMap<>();
        prefixFrequency.put(0, 1);                       // empty prefix base case
        int runningSum = 0;
        int count = 0;
        for (int value : nums) {
            runningSum += value;
            count += prefixFrequency.getOrDefault(runningSum - k, 0);
            prefixFrequency.put(runningSum, prefixFrequency.getOrDefault(runningSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] inputs = {{1, 1, 1}, {1, -1, 0}};
        int[] targets = {2, 0};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("nums = " + Arrays.toString(inputs[i]) + ", k = " + targets[i]
                    + " -> " + subarraySum(inputs[i], targets[i]));
        }
    }
}
