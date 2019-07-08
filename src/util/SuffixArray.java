package util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Suffix {
  private String s;
  private int index;

  public Suffix(String s, int index) {
    this.s = s;
    this.index = index;
  }

  @Override
  public String toString() {
    return this.s.substring(this.index);
  }
}

public class SuffixArray {
  private List<Suffix> suffixes;
  private String s;
  private Comparator<Suffix> asc = (s1, s2) -> s1.toString().compareTo(s2.toString());

  public SuffixArray(String s) {
    this.s = s;
    this.suffixes = IntStream.range(0, s.length())
        .mapToObj(i -> new Suffix(s, i))
        .sorted(asc)
        .collect(Collectors.toList());
  }

  public Suffix suffixOf(String substr) {
    int i = 0;
    int j = suffixes.size();
    while (i < j) {
      int m = (i + j) / 2;
      Suffix suff = suffixes.get(m);
      if (suff.toString().indexOf(substr) == 0) {
        i = m;
        break;
      }
      int cmp = substr.compareTo(suff.toString());
      if (cmp > 0) {
        i = m + 1;
      }
      else if (cmp < 0) {
        j = m;
      }
    }
    if (i < suffixes.size() && suffixes.get(i).toString().indexOf(substr) == 0) {
      return suffixes.get(i);
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    System.out.println(new SuffixArray("Hello world").suffixOf("rl"));
  }
}
