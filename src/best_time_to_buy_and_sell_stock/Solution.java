package best_time_to_buy_and_sell_stock;

public class Solution {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    int min = 0;
    int max_diff = 0;
    for (int i = 1; i < prices.length; i++) {
      int diff = Math.max(0, prices[i] - prices[min]);
      max_diff = Math.max(diff, max_diff);
      if (prices[i] < prices[min]) {
        min = i;
      }
    }
    return max_diff;
  }
}
