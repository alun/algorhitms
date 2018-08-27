package remove_element;

import java.util.Arrays;

class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] == val) j++;
            else nums[i++] = nums[j++];
        }
        return i;
    }

    public static void main(String[] args) {
        int [] nums = new int [] { 3, 2, 2, 3 };
        System.out.println(new Solution().removeElement(nums, 3));
        System.out.println(Arrays.toString(nums));
    }
}

