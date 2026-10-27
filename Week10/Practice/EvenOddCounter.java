import java.util.Arrays;

/** Counts even and odd numbers in a list. */
public class EvenOddCounter {

    public static int[] countEvenAndOdd(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List cannot be null.");
        }
        int evenCount = 0;
        int oddCount = 0;
        for (int number : numbers) {
            if (number % 2 == 0) evenCount++;
            else oddCount++;
        }
        return new int[]{evenCount, oddCount};
    }

    public static void main(String[] args) {
        int[] numbers = {3, 8, 12, 5, 7, 10};
        int[] counts = countEvenAndOdd(numbers);
        System.out.println("Input: " + Arrays.toString(numbers));
        System.out.println("Even: " + counts[0]);
        System.out.println("Odd: " + counts[1]);
    }
}
