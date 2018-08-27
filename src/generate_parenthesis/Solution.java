package generate_parenthesis;

import java.util.*;

public class Solution {
    public List<String> generateParenthesis(int n) {
        if (n == 0) return Collections.emptyList();
        ArrayList<Set<String>> res = new ArrayList<>(
                Arrays.asList(
                     new HashSet<>(Arrays.asList("()"))
                )
            );
        for (int m = 1; m < n; m++) {
            res.add(new HashSet<>());
            for (String s: res.get(m - 1))
                res.get(m).add("(" + s + ")");

            for (int k = 1; k < (m + 1); k++) {
                int v = m + 1 - k;
                for (String s1 : res.get(k - 1))
                    for (String s2 : res.get(v - 1))
                        res.get(m).add(s1 + s2);
            }
        }
        return new ArrayList<>(res.get(res.size() - 1));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(6));
    }
}

