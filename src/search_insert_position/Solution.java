package search_insert_position;

public class Solution {
  /**
   * Finds insert position for target in the nums sorted array
   */
  public int searchInsert(int[] nums, int target) {
    if (nums == null) return 0;
    int l = 0;
    int r = nums.length;
    int i = (l + r) / 2;
    while (l < r && target != nums[i]) {
      if (target > nums[i]) {
        l = i + 1;
      } else if (target < nums[i]) {
        r = i;
      }
      i = (l + r) / 2;
    }
    return i;
  }
}


