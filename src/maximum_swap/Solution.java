package maximum_swap;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  private List<Integer> digits(int num) {
    List<Integer> res = new ArrayList<>();
    while (num > 0) {
      res.add(0, num % 10);
      num /= 10;
    }
    return res;
  }
  private void findMax(int dig, List<Integer> digits) {
    if (dig >= digits.size()) {
      return;
    }
    int value = digits.get(dig);
    int max = value;
    int max_i = -1;
    for (int i = digits.size() - 1; i >= dig + 1; i--) {
      if (digits.get(i) > max) {
        max = digits.get(i);
        max_i = i;
      }
    }
    if (value == max) {
      findMax(dig + 1, digits);
    } else {
      int t = digits.get(dig);
      digits.set(dig, digits.get(max_i));
      digits.set(max_i, t);
    }
  }
  private int join(List<Integer> digits) {
    int res = 0;
    for (int dig: digits) {
      res *= 10;
      res += dig;
    }
    return res;
  }
  public int maximumSwap(int num) {
    List<Integer> digs = digits(num);
    findMax(0, digs);
    return join(digs);
  }
}



