package maximum_subarray;

public class Solution {
    static int maxSubArray(int a[])
    {
        int size = a.length;
        int max_so_far = a[0];
        int current_max = a[0];
        int max_i = 0;
        int max_j = 0;

        for (int i = 1; i < size; i++)
        {
            if (a[i] > current_max + a[i]) {
                current_max = a[i];
                max_i = i;
            }
            else {
                current_max = current_max + a[i];
            }

            if (max_so_far < current_max) {
                max_j = i;
                max_so_far = current_max;
            }
        }
        System.out.println(max_i);
        System.out.println(max_j);
        return max_so_far;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int [] {-2, 3, -1, 4, -10, 5, -8}));
    }
}



