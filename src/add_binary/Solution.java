package add_binary;

public class Solution {
  public String addBinary(String a, String b) {
    int carry = 0;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
      char char_a = '0';
      char char_b = '0';
      if (i < a.length()) {
        char_a = a.charAt(a.length() - 1 - i);
      }
      if (i < b.length()) {
        char_b = b.charAt(b.length() - 1 - i);
      }
      int res = carry + (char_a - '0') + (char_b - '0');
      carry = res / 2;
      res %= 2;
      result.insert(0, (char) ('0' + res));
    }
    if (carry > 0) {
      result.insert(0, '1');
    }
    return result.toString();
  }
}
