import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Chosen solution (HashSet): Time O(n) average, Space O(n).
 * Alternative (sort, then compare neighbours): Time O(n log n), Space O(1) extra if sorted in place.
 * Trade-off: the hash set buys speed with memory; sorting saves memory but is slower, which
 * matters when millions of IDs must be checked quickly.
 */
public class TransactionDuplicateCheck {

    public static boolean hasDuplicate(String[] transactionIds) {
        if (transactionIds == null) {
            throw new IllegalArgumentException("Transaction list cannot be null.");
        }
        Set<String> seen = new HashSet<>();
        for (String id : transactionIds) {
            if (!seen.add(id)) {          // add() returns false when the ID is already present
                return true;
            }
        }
        return false;
    }

    /** Memory-saving alternative: sort a copy and compare neighbours. */
    public static boolean hasDuplicateBySorting(String[] transactionIds) {
        if (transactionIds == null) {
            throw new IllegalArgumentException("Transaction list cannot be null.");
        }
        String[] sorted = transactionIds.clone();
        Arrays.sort(sorted);
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i].equals(sorted[i - 1])) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] batchOne = {"TXN1001", "TXN1002", "TXN1003", "TXN1002", "TXN1004"};
        String[] batchTwo = {"TXN2001", "TXN2002", "TXN2003"};
        System.out.println(hasDuplicate(batchOne));
        System.out.println(hasDuplicate(batchTwo));
        System.out.println("Sorting alternative: " + hasDuplicateBySorting(batchOne) + ", " + hasDuplicateBySorting(batchTwo));
    }
}
