package longest_common_prefix;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int ref = 0, j = 0;

        for (int i = 1; i < strs.length; i++)
            if (strs[i].length() < strs[ref].length())
                ref = i;

        charsLoop:
        for (j = 0; j < strs[ref].length(); ++j)
            for (int i = 0; i < strs.length; i++) {
                if (i == ref) continue;
                if (strs[ref].charAt(j) != strs[i].charAt(j))
                    break charsLoop;
            }

        return strs[ref].substring(0, j);
    }

    public static void main(String[] args) {
        System.out.println("\"" + new Solution().longestCommonPrefix(new String[] {"all", "albert", "alf"}) + "\"");
    }
}

