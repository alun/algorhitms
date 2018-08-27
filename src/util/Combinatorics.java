package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Combinatorics {

  public static Stream<List<Integer>> permutations(int n) {
    if (n == 0) {
      return Stream.empty();
    }
    if (n == 1) {
      return Stream.of(Arrays.asList(0));
    }
    return permutations(n - 1).flatMap(p ->
      IntStream.rangeClosed(0, p.size()).mapToObj(i -> {
        List<Integer> l = new ArrayList<>(p);
        l.add(l.size() - i, n - 1);
        return l;
      })
    );
  }

}
