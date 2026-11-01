import java.util.Arrays;

/**
 * Single pass: Time O(n), Space O(1).
 * Checking every pair of days: Time O(n^2), Space O(1).
 * At scale (for example a million prices) O(n) needs about a million steps, while O(n^2) needs about a trillion.
 */
public class BestSingleTradeProfit {

    public static int maxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Prices cannot be null.");
        }
        if (prices.length < 2) return 0;
        int lowestSoFar = prices[0];
        int bestProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            lowestSoFar = Math.min(lowestSoFar, prices[i]);
            bestProfit = Math.max(bestProfit, prices[i] - lowestSoFar);
        }
        return bestProfit;
    }

    public static int maxProfitAllPairs(int[] prices) {
        int bestProfit = 0;
        for (int buy = 0; buy < prices.length; buy++) {
            for (int sell = buy + 1; sell < prices.length; sell++) {
                bestProfit = Math.max(bestProfit, prices[sell] - prices[buy]);
            }
        }
        return bestProfit;
    }

    public static void main(String[] args) {
        int[][] samples = {{7, 1, 5, 3, 6, 4}, {7, 6, 4, 3, 1}};
        for (int[] prices : samples) {
            System.out.println("prices = " + Arrays.toString(prices) + " -> " + maxProfit(prices)
                    + " (all pairs check: " + maxProfitAllPairs(prices) + ")");
        }
    }
}
