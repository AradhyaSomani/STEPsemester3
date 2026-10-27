import java.util.Scanner;

/** Checks whether numbers greater than 1 are prime. */
public class PrimeNumberChecker {

    public static boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalArgumentException("Number must be greater than 1: " + number);
        }
        for (int divisor = 2; (long) divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                try {
                    int number = Integer.parseInt(token);
                    System.out.println(number + (isPrime(number) ? " is prime" : " is not prime"));
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + token + "' is not a valid integer.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
