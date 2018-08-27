package remove_duplicates_from_sorted_array;

public class Solution {

    public int removeDuplicates(int[] nums) {
        int i = 0, j = i + 1, l = 1;
        if (nums == null || nums.length == 0) return 0;
        while (i < nums.length && j < nums.length) {
            if (nums[i] == nums[j]) j++;
            else {
                nums[i + 1] = nums[j];
                i++;
                j++;
                l++;
            }
        }
        return l;
    }
}
