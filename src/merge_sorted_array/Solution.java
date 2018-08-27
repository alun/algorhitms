package merge_sorted_array;

public class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int k = 0;
    while (k < n) {
      int v = nums2[k++];
      int i = 0;
      int j = m;
      while (i < j) {
        int mid = (i + j) / 2;
        if (v < nums1[mid]) {
          j = mid;
        } else {
          i = mid + 1;
        }
      }
      for (j = m - 1; j >= i; j--) {
        nums1[j + 1] = nums1[j];
      }
      nums1[i] = v;
      m += 1;
    }
  }
}
