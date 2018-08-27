package palindrome_number;

class Solution {
    int digitAt(int num, int pos) {
        return (num / (int) Math.pow(10, pos)) % 10;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        // find the length
        int l = 0, n = x;
        while (n > 0) {
            n /= 10;
            l += 1;
        }
        for (int i = 0; i < l / 2; i++) {
            if (digitAt(x, i) != digitAt(x, l - 1 - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(1));
    }
}
