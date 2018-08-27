package fibonacci;

public class Solution {

    public int fib(int n) {
        int[] last = new int[] { 1, 1 };
        for (int i = 2; i <= n; i++) {
            int next = Math.addExact(last[0], last[1]);
            last[0] = last[1];
            last[1] = next;
        }
        return last[1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fib(1));
        System.out.println(new Solution().fib(2));
        System.out.println(new Solution().fib(55));
    }
}

