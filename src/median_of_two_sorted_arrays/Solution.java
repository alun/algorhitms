package median_of_two_sorted_arrays;

// incomplete
// https://leetcode.com/problems/median-of-two-sorted-arrays/description/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int imin = 0;
        int imax = nums1.length;
        boolean isEven = (nums1.length + nums2.length) % 2 == 0;
        while (imin <= imax) {
            int i = (imax + imin) / 2;
            int j = (nums1.length + nums2.length + (isEven ? 0 : 1)) / 2 - i;
            if (i > 0 && nums1[i - 1] <= nums2[j] || i == 0) {
                if (i < nums1.length && nums2[j - 1] <= nums1[i] || i == nums1.length) {
                    int left, right;
                    if (i == 0) left = nums2[j - 1];
                    else if (j == 0) left = nums1[i - 1];
                    else left = Math.max(nums1[i - 1], nums2[j - 1]);

                    if (i == nums1.length) right = nums2[j];
                    else if (j == nums2.length) right = nums1[i];
                    else right = Math.min(nums1[i], nums2[j]);

                    return isEven ? (left + right) / 2. : right;
                } else imin = i + 1;
            } else imax = i - 1;
        }
        throw new IllegalArgumentException("Bad arguments");
    }
}
