/** Classifies seating rows as Quiet or Buzzing using a reusable averaging helper. */
public class SeatingGridOptimizer {

    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0.0;
        }
        int total = 0;
        for (int value : row) {
            total += value;
        }
        return (double) total / row.length;
    }

    public static String classifyRows(int[][] seatingScores, int threshold) {
        if (seatingScores == null) {
            throw new IllegalArgumentException("Seating grid cannot be null.");
        }
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < seatingScores.length; row++) {
            String zone = rowAverage(seatingScores[row]) < threshold ? "Quiet Zone" : "Buzzing Zone";
            if (row > 0) result.append(" | ");
            result.append("Row ").append(row).append(": ").append(zone);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int[][] grid = {{40, 50, 45}, {85, 90, 95}, {30, 20, 25}};
        System.out.println(classifyRows(grid, 60));
        int[][] jagged = {{80}, {10, 20, 30, 40}};
        System.out.println(classifyRows(jagged, 60));
    }
}
