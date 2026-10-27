import java.util.Arrays;

/** Maximum profit from one buy and one later sell, in a single pass. */
public class StockProfitCalculator {

    public static int maxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Prices cannot be null.");
        }
        if (prices.length == 0) {
            return 0;
        }
        int lowestPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            lowestPrice = Math.min(lowestPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - lowestPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[][] inputs = {{7, 1, 5, 3, 6, 4}, {7, 6, 4, 3, 1}};
        for (int[] prices : inputs) {
            System.out.println("prices = " + Arrays.toString(prices) + " -> " + maxProfit(prices));
        }
        try {
            maxProfit(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
