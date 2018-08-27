package plus_one;

public class Solution {
  public int[] plusOne(int[] digits) {
    int carryon = 1;
    for (int i = digits.length - 1; i >= 0; i--) {
      int incr = digits[i] + carryon;
      carryon = incr / 10;
      digits[i] = incr % 10;
    }
    if (carryon == 1) {
      int[] res = new int[digits.length + 1];
      System.arraycopy(digits, 0, res, 1, digits.length);
      res[0] = 1;
      return res;
    } else {
      return digits;
    }
  }
}
