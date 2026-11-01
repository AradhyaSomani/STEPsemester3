import java.util.Arrays;

/** Selection sort (descending) that returns the number of swaps actually made. */
public class PrizeTableSorter {

    public static int prizeTable(int[] marks) {
        if (marks == null) {
            throw new IllegalArgumentException("Marks cannot be null.");
        }
        int swaps = 0;
        for (int i = 0; i < marks.length - 1; i++) {
            int largestIndex = i;
            for (int j = i + 1; j < marks.length; j++) {
                if (marks[j] > marks[largestIndex]) {
                    largestIndex = j;
                }
            }
            if (largestIndex != i) {          // count only real swaps
                int temp = marks[i];
                marks[i] = marks[largestIndex];
                marks[largestIndex] = temp;
                swaps++;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        int[][] samples = {{45, 90, 60, 75}, {80, 70, 60}, {10, 40, 30, 20, 50}};
        for (int[] marks : samples) {
            int swaps = prizeTable(marks);
            System.out.println(Arrays.toString(marks) + "   swaps = " + swaps);
        }
    }
}
