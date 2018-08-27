package longest_palindromic_substring;

public class Solution {
    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n == 0) return "";
        for (int k = n; k >= 1; k--) {
            for (int i = 0; i <= n - k; i++) {
                int j = k + i - 1;
                if (checkPalindrome(arr, i, j)) return s.substring(i, j + 1);
            }
        }
        return s.substring(0, 1);
    }

    private boolean checkPalindrome(char[] arr, int i, int j) {
        for (int t = 0; t <= (j - i) / 2; t++) {
            if (arr[i + t] != arr[j - t]) return false;
        }
        return true;
    }

}

