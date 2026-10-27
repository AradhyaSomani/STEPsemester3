import java.util.Random;

/** BMI calculator and wellness report for a team of people. */
public class BmiCalculator {

    private static final int TEAM_SIZE = 10;
    private static final double UNDERWEIGHT_LIMIT = 18.5;
    private static final double NORMAL_LIMIT = 25.0;
    private static final double OVERWEIGHT_LIMIT = 30.0;

    public static double calculateBmi(double height, double weight) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Height and weight must be positive.");
        }
        return weight / (height * height);
    }

    public static String getBmiStatus(double bmi) {
        if (bmi < UNDERWEIGHT_LIMIT) return "Underweight";
        if (bmi < NORMAL_LIMIT) return "Normal";
        if (bmi < OVERWEIGHT_LIMIT) return "Overweight";
        return "Obese";
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights.length != weights.length) {
            throw new IllegalArgumentException("Heights and weights must have equal length.");
        }
        System.out.println("Person | Height (m) | Weight (kg) | BMI   | Status");
        System.out.println("-------+------------+-------------+-------+------------");
        for (int i = 0; i < heights.length; i++) {
            double bmi = calculateBmi(heights[i], weights[i]);
            System.out.printf("%-6d | %-10.2f | %-11.1f | %-5.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        double[] heights = new double[TEAM_SIZE];
        double[] weights = new double[TEAM_SIZE];
        for (int i = 0; i < TEAM_SIZE; i++) {
            heights[i] = Math.round((1.50 + random.nextDouble() * 0.40) * 100) / 100.0;
            weights[i] = Math.round((45 + random.nextDouble() * 60) * 10) / 10.0;
        }
        try {
            printWellnessReport(heights, weights);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
