package jump_game;

class Solution {

  public boolean canJump(int[] nums, int target) {
    for (int i = 0; i < target; i++) {
      if (nums[i] >= target - i) {
        return (i == 0 ? true : canJump(nums, i));
      }
    }
    return false;
  }

  public boolean canJump(int[] nums) {
    if (nums.length == 1) {
      return true;
    }
    return canJump(nums, nums.length - 1);
  }
}
