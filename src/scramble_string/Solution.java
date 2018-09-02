package scramble_string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
  private Map<List<String>, Boolean> memo = new HashMap<>();

  public boolean isScramble(String s1, String s2) {
    if (s1 == null && s2 == null) {
      return true;
    }
    if (s1 == null || s2 == null) {
      return false;
    }
    if (s1.length() == 0 && s2.length() == 0) {
      return true;
    }
    if (s1.length() != s2.length()) {
      return false;
    }
    if (s1.equals(s2)) {
      return true;
    }
    List<Integer> s1_chars = s1.chars().mapToObj(Integer::valueOf).collect(Collectors.toList());
    List<Integer> s2_chars = s2.chars().mapToObj(Integer::valueOf).collect(Collectors.toList());
    s1_chars.removeAll(s2_chars);
    if (s1_chars.size() > 0) {
      return false;
    }
    List<String> key = Arrays.asList(s1, s2);
    Boolean b = memo.get(key);
    if (b != null) {
      return b;
    }
    for (int i = 1; i < s1.length(); i++) {
      int l = s1.length();
      int j = l - i;
      boolean scramble =
          isScramble(s1.substring(0, i), s2.substring(0, i)) &&
              isScramble(s1.substring(i, l), s2.substring(i, l));
      scramble = scramble || (
          isScramble(s1.substring(0, i), s2.substring(j, l)) &&
              isScramble(s1.substring(i, l), s2.substring(0, j))
      );
      if (scramble) {
        return true;
      }
    }
    memo.put(key, false);
    return false;
  }
}
