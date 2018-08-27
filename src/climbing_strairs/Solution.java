package climbing_strairs;

public class Solution {
  public int climbStairs(int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return 1;
    }
    int[] memo = {1, 1};
    for (int i = 2; i <= n; i++) {
      int next = memo[0] + memo[1];
      memo[0] = memo[1];
      memo[1] = next;
    }
    return memo[1];
  }
}

