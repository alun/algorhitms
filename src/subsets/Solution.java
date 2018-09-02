package subsets;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

  private List<List<Integer>> traverseSubsets(List<List<Integer>> acc,
                                              Set<Integer> current,
                                              Set<Integer> rest) {
    acc.add(new ArrayList<>(current));

    int max = current.stream().mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
    for (Integer elem : rest) {
      if (elem > max) {
        Set<Integer> nextCurrent = new HashSet<>(current);
        Set<Integer> nextRest = new HashSet<>(rest);
        nextCurrent.add(elem);
        nextRest.remove(elem);
        traverseSubsets(acc, nextCurrent, nextRest);
      }
    }

    return acc;
  }

  public List<List<Integer>> subsets(int[] nums) {
    return traverseSubsets(new ArrayList<>(),
        new HashSet<>(),
        Arrays.stream(nums).mapToObj(Integer::valueOf).collect(Collectors.toSet())
    );
  }
}



