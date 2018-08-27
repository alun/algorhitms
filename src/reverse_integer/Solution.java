package reverse_integer;

// https://leetcode.com/problems/reverse-integer/description/
public class Solution {
    public int reverse(int x) {
        int result = 0;
        int next;
        while (x != 0) {
            next = x % 10;
            x = x / 10;
            try {
                result = Math.addExact(Math.multiplyExact(result, 10), next);
            } catch (ArithmeticException e) {
                return 0;
            }
        }
        return result;
    }
}
