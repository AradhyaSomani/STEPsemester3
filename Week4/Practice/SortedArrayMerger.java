import java.util.Arrays;

/** Merges two sorted arrays into one sorted array using two pointers. */
public class SortedArrayMerger {

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Arrays cannot be null.");
        }
        int[] merged = new int[arr1.length + arr2.length];
        int firstIndex = 0, secondIndex = 0, mergedIndex = 0;

        while (firstIndex < arr1.length && secondIndex < arr2.length) {
            if (arr1[firstIndex] <= arr2[secondIndex]) {
                merged[mergedIndex++] = arr1[firstIndex++];
            } else {
                merged[mergedIndex++] = arr2[secondIndex++];
            }
        }
        while (firstIndex < arr1.length) {
            merged[mergedIndex++] = arr1[firstIndex++];
        }
        while (secondIndex < arr2.length) {
            merged[mergedIndex++] = arr2[secondIndex++];
        }
        return merged;
    }

    public static void main(String[] args) {
        int[][][] cases = {{{1, 3, 5}, {2, 4, 6}}, {{}, {1, 2, 3}}};
        for (int[][] pair : cases) {
            System.out.println("arr1 = " + Arrays.toString(pair[0]) + ", arr2 = " + Arrays.toString(pair[1])
                    + " -> " + Arrays.toString(mergeSortedArrays(pair[0], pair[1])));
        }
    }
}
