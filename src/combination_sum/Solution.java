package combination_sum;

import java.util.*;

public class Solution {
  private int target = 0;
  private int[] candidates = null;
  private Map<List<Integer>, Set<List<Integer>>> memo = new HashMap<>();

  Set<List<Integer>> findCombos(List<Integer> cur, int curSum) {

    if (memo.containsKey(cur)) {
      return memo.get(cur);
    }

    if (curSum > target) {
      return Collections.emptySet();
    }
    if (curSum == target) {
      return new HashSet<>(Arrays.asList(cur));
    }

    Set<List<Integer>> res = new HashSet<>();
    for (int n : candidates) {
      List<Integer> next = new ArrayList<>(cur);
      next.add(n);
      next.sort(Comparator.comparing(Integer::intValue));
      res.addAll(findCombos(next, curSum + n));
    }

    memo.put(cur, res);
    return res;
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    this.target = target;
    this.candidates = candidates;
    return new ArrayList<>(findCombos(new ArrayList<>(), 0));
  }
}


