package count_and_say;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Solution {
  public String countAndSay(int n) {
    if (n == 0) {
      throw new IllegalArgumentException("0 doesn't exist");
    }
    if (n == 1) {
      return "1";
    } else {
      final String prev = countAndSay(n - 1);

      return prev.chars()
          .collect(
              () -> new ArrayList<Pair<Character, Integer>>(),
              (acc, c) -> {
                char next = (char) c;
                if (acc.size() > 1) {
                  Pair<Character, Integer> last = acc.get(acc.size() - 1);
                  if (last.getKey() == next) {
                    acc.set(acc.size() - 1, new Pair<>(next, last.getValue() + 1));
                    return;
                  }
                }
                acc.add(new Pair<>(next, 1));
              },
              Collection::addAll)
          .stream().map(p -> "" + p.getValue() + p.getKey())
          .collect(Collectors.joining());
    }
  }
}
