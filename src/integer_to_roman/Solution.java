package integer_to_roman;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
  static final String[] DIGITS  = { "M", "D", "C", "L", "X", "V", "I" };
  static final int[] VALUES  = { 1000, 500, 100, 50, 10, 5, 1 };

  public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    int last_whole = 0;
    int whole = 0;
    for (int i = 0; i < DIGITS.length && num > 0; i++) {
      last_whole = whole;
      whole = num / VALUES[i];
      num = num % VALUES[i];
      if (i > 0 && i % 2 == 0 && whole == 4) {
        if (last_whole == 0) {
          sb.append(DIGITS[i] + DIGITS[i-1]);
        }
        else {
          sb.append(DIGITS[i] + DIGITS[i-2]);
        }
      }
      else if (!(i % 2 == 1 && (num / VALUES[i+1]) == 4)) {
        final String single = DIGITS[i];
        String digits = Stream.generate(() -> single)
            .limit(whole)
            .collect(Collectors.joining());
        sb.append(digits);
      }
    }
    return sb.toString();
  }
}



