import java.util.Scanner;

/** Prints the digit sum and reversed form of positive integers. */
public class DigitSumAndReverse {

    public static int sumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static int reverseDigits(int number) {
        int reversed = 0;
        while (number > 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        return reversed;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                try {
                    int number = Integer.parseInt(token);
                    if (number <= 0) {
                        throw new IllegalArgumentException("Number must be positive: " + number);
                    }
                    System.out.println("Sum of digits: " + sumDigits(number));
                    System.out.println("Reverse: " + reverseDigits(number));
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + token + "' is not a valid integer.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
