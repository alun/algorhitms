package lexicographical_numbers;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  void addNextLexical(List<Integer> acc, int max, int cur) {
    if (cur >= max) {
      return;
    }
    acc.add(cur);
    addNextLexical(acc, max, cur * 10);
    if (cur % 10 < 9) {
      addNextLexical(acc, max, cur + 1);
    }
  }

  public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    addNextLexical(res, n + 1, 1);
    return res;
  }
}


