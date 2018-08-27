package longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> metPos = new HashMap<>();
        int i = 0, j = 1, best_i = 0, best_j = 1;
        if (s == null || s.length() == 0) return 0;
        for (j = 1; j <= s.length(); j++) {
            char c = s.charAt(j - 1);
            if (metPos.containsKey(c)) {
                int new_i = metPos.get(c) + 1;
                for (int t = i; t < new_i; t++) metPos.remove(s.charAt(t));
                i = new_i;
            }
            if (j - i > best_j - best_i) {
                best_i = i;
                best_j = j;
            }
            metPos.put(c, j - 1);
        }

        return best_j - best_i;
    }
}


