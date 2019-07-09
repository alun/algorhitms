package sherlock_and_valid_string;

import java.io.*;
import java.util.*;

// https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem

public class Solution {

  // Complete the isValid function below.
  static String isValid(String s) {
    Map<Integer, Integer> counts = new HashMap<>();
    int maxCount = -1;
    for (int i = 0; i < s.length(); i++) {
      int cp = s.codePointAt(i);
      int count = counts.getOrDefault(cp, 0);
      count += 1;
      if (count > maxCount) {
        maxCount = count;
      }
      counts.put(cp, count);
    }

    int zerosCount = 0;
    int negativeCount = 0;
    int lastNeg = -1;
    for (Integer count : counts.values()) {
      if (count - maxCount == 0) {
        zerosCount += 1;
      } else if (count - maxCount < 0) {
        negativeCount += 1;
        if (negativeCount > 1 && (lastNeg != -1 || count - maxCount != -1)) return "NO";
        lastNeg = count - maxCount;
      }
    }
    if (zerosCount == 1 || negativeCount == 1 || negativeCount == 0) return "YES";
    else return "NO";
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String s = scanner.nextLine();

    String result = isValid(s);

    bufferedWriter.write(result);
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
