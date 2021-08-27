package next_permutation;

class Solution {

  public void nextPermutation(int[] nums) {
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



}
