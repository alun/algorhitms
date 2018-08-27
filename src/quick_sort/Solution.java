package quick_sort;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    void quickSort(int[] arr) {
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(0, arr.length - 1));
        while (!stack.empty()) {
            Pair<Integer, Integer> pair = stack.pop();
            int i = pair.getKey();
            int j = pair.getValue();
            int m = (i + j)/2;
            int pivot = arr[m];

            if (j - i > 1) {
                stack.push(new Pair<>(i, m - 1));
                stack.push(new Pair<>(m + 1, j));
            }

            while (i < j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i < j) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    i++;
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] arr = new int[] {3, 2, 1, 2, -1, 0, 9};
        new Solution().quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
