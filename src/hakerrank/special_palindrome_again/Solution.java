package hakerrank.special_palindrome_again;

// https://www.hackerrank.com/challenges/special-palindrome-again/problem

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Solution {

  static long substrCount(int n, String s) {
    int count = s.length() == 0 ? 0 : 1;
    for (int i = 1; i < s.length(); i++) {
      count++;
      int c = s.codePointAt(i - 1);
      for (int t = 1;
           i >= t &&
               i < s.length() - t &&
               s.codePointAt(i - t) == c &&
               s.codePointAt(i + t) == c;
           t++, count++
      ) {
      }
      for (int t = 1;
           i >= t &&
               i <= s.length() - t &&
               s.codePointAt(i - t) == c &&
               s.codePointAt(i + t - 1) == c;
           t++, count++
      ) {
      }
    }
    return count;
  }

  @Test
  void expectations() {
    assertEquals(substrCount(0, "aabaa"), 9);
    assertEquals(substrCount(0, "aabbaa"), 9);
    assertEquals(substrCount(0, "aababaa"), 12);
  }

}
