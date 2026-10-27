import java.util.Scanner;

/** Calculates factorials of non-negative integers. */
public class FactorialCalculator {

    private static final int MAX_SUPPORTED = 20;     // 20! is the largest that fits in a long

    public static long calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + number);
        }
        if (number > MAX_SUPPORTED) {
            throw new ArithmeticException("Result overflows long for n > " + MAX_SUPPORTED);
        }
        long result = 1;                              // handles 0! = 1
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                try {
                    System.out.println(calculateFactorial(Integer.parseInt(token)));
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + token + "' is not a valid integer.");
                } catch (IllegalArgumentException | ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
