package interleaving_string;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Test;

public class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1 == null || s2 == null || s3 == null) {
      throw new IllegalArgumentException("Null is not accepted");
    }
    if (s3.length() != s1.length() + s2.length()) {
      return false;
    }
    Queue<List<Integer>> discovered = new LinkedBlockingQueue<>();
    Set<List<Integer>> processed = new HashSet<>();
    discovered.add(Arrays.asList(0, 0, 0));
    boolean result = false;
    while (discovered.size() > 0) {
      List<Integer> indices = discovered.poll();
      int i1 = indices.get(0);
      int i2 = indices.get(1);
      int i3 = indices.get(2);

      if (!processed.contains(indices)) {
        System.out.println(indices);
        if (i1 >= s1.length() && i2 >= s2.length() && i3 >= s3.length()) {
          return true;
        }
        if (i1 < s1.length() && s3.charAt(i3) == s1.charAt(i1)) {
          discovered.add(Arrays.asList(i1 + 1, i2, i3 + 1));
        }
        if (i2 < s2.length() && s3.charAt(i3) == s2.charAt(i2)) {
          discovered.add(Arrays.asList(i1, i2 + 1, i3 + 1));
        }
        processed.add(indices);
      }
    }

    return false;
  }

  @Test
  public void runVerify() {
    System.out.println(isInterleave(
    "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
    "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
    "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"
    ));
  }
}
