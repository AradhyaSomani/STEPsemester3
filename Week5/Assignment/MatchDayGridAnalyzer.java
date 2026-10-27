/** Flags "Power Surge" matches using a reusable row-average helper (jagged rows supported). */
public class MatchDayGridAnalyzer {

    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0.0;
        }
        int total = 0;
        for (int runs : row) {
            total += runs;
        }
        return (double) total / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        if (runsPerOver == null) {
            throw new IllegalArgumentException("Match grid cannot be null.");
        }
        StringBuilder result = new StringBuilder();
        for (int match = 0; match < runsPerOver.length; match++) {
            String label = rowAverage(runsPerOver[match]) >= threshold ? "Power Surge" : "Normal";
            if (match > 0) result.append(" | ");
            result.append("Match ").append(match).append(": ").append(label);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyMatches(new int[][]{{4, 6, 8}, {10, 12, 14}, {2, 3, 1}}, 8));
        System.out.println(classifyMatches(new int[][]{{9}, {1, 2}, {8, 8, 8, 8}}, 8));
    }
}
