package count_of_smaller_numbers_after_self;

import java.util.*;

class Solution {
  public List<Integer> countSmaller(int[] nums) {
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
    Map<Integer, List<Integer>> position = new HashMap<>();
    Set<Integer> indices = new HashSet<>();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      List<Integer> list = position.get(nums[i]);
      if (list == null) {
        list = new ArrayList<>();
      }
      list.add(0, i);
      position.put(nums[i], list);
      heap.add(nums[i]);
      indices.add(i);
      result.add(0);
    }

    while (heap.size() > 0) {
      Integer elem = heap.poll();
      int i = position.get(elem).remove(0);
      indices.remove(i);
      int count = (int) indices.stream().filter(j -> j > i).count();
      result.set(i, count);
    }
    return result;
  }
}
