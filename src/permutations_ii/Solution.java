package permutations_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class Solution {

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    int[] current = Arrays.copyOf(nums, nums.length);

    result.add(toList(current));
    nextPermutation(current);
    while (!Arrays.equals(nums, current)) {
      result.add(toList(current));
      nextPermutation(current);
    }

    return result;
  }

  private List<Integer> toList(int[] array) {
    return Arrays.stream(array).mapToObj(Integer::valueOf).collect(Collectors.toUnmodifiableList());
  }

  private void nextPermutation(int[] nums) {
    for (int i = nums.length - 1; i > 0; i--) {

      if (nums[i - 1] < nums[i]) {

        int swap_index = nums.length;
        for (int j = i; j < nums.length; j++) {
          if (swap_index == nums.length
              || (nums[j] > nums[i - 1] && nums[j] <= nums[swap_index])) {
            swap_index = j;
          }
        }
        swap(nums, i - 1, swap_index);
        reverse(nums, i, nums.length);
        return;
      }

    }

    reverse(nums, 0, nums.length);
  }

  private void swap(int [] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  private void reverse(int [] nums, int start, int end) {
    for (int i = 0; i < (end - start) / 2; i++) {
      swap(nums, start + i, end - 1 - i);
    }
  }

  @Test
  void itFindPermutations() {
    System.out.println(
        permuteUnique(new int[] {1,2,3})
    );

  }
}
