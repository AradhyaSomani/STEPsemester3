/** Compares totals of two warehouse sections and finds the highest quantity. */
public class WarehouseInventoryBalancer {

    public static int calculateTotal(int[] quantities) {
        int total = 0;
        for (int quantity : quantities) {
            total += quantity;
        }
        return total;
    }

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length) {
            throw new IllegalArgumentException("Both sections must have the same number of items.");
        }
        if (sectionA.length == 0) {
            throw new IllegalArgumentException("Sections cannot be empty.");
        }
        int totalA = calculateTotal(sectionA);
        int totalB = calculateTotal(sectionB);
        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int highest = sectionA[0];
        String highestSection = "A";
        int highestIndex = 0;
        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i]; highestSection = "A"; highestIndex = i;
            }
            if (sectionB[i] > highest) {
                highest = sectionB[i]; highestSection = "B"; highestIndex = i;
            }
        }
        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB
                + " | Status: " + status + " | Highest Quantity: " + highest
                + " (Section " + highestSection + ", Item " + (highestIndex + 1) + ")");
    }

    public static void main(String[] args) {
        try {
            analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});
            analyzeInventory(new int[]{5, 9}, new int[]{1, 2, 3});   // triggers exception
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
