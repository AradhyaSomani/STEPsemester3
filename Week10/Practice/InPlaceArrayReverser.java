import java.util.Arrays;

/** Reverses an array in place using two pointers. */
public class InPlaceArrayReverser {

    public static void reverseInPlace(int[] tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        int left = 0;
        int right = tokens.length - 1;
        while (left < right) {
            int temp = tokens[left];
            tokens[left] = tokens[right];
            tokens[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] tokens = {11, 22, 33, 44};
        System.out.println("Before: " + Arrays.toString(tokens));
        reverseInPlace(tokens);
        System.out.println("After:  " + Arrays.toString(tokens));
    }
}
