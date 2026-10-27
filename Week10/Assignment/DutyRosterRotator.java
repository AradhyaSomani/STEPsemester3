import java.util.Arrays;

/**
 * Rotates a roster right by k places using index arithmetic.
 * Time: O(n). Space: O(n) for the new array.
 */
public class DutyRosterRotator {

    public static String[] rotateRoster(String[] names, int k) {
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("Roster cannot be empty.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k cannot be negative.");
        }
        int size = names.length;
        int shift = k % size;                     // remove full turns
        String[] rotated = new String[size];
        for (int i = 0; i < size; i++) {
            rotated[(i + shift) % size] = names[i];
        }
        return rotated;
    }

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E"};
        int[] shifts = {2, 7, 1_000_000_000};
        for (int k : shifts) {
            System.out.println("names = " + Arrays.toString(names) + ", k = " + k
                    + " -> " + Arrays.toString(rotateRoster(names, k)));
        }
        try {
            rotateRoster(names, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
