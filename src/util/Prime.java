package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class Prime {

  /**
   * Sieve of Eratosthene
   * @return endless stream of prime ints
   */
  public static IntStream stream() {
    final List<Integer> primes = new ArrayList<>();
    primes.add(2);
    return IntStream.concat(IntStream.of(2), IntStream.generate(() -> {
      int i = primes.get(primes.size() - 1);
      while (true) {
        final int candidate = i;
        if (primes.stream().allMatch(p -> candidate % p != 0)) {
          break;
        }
        i++;
      }
      primes.add(i);
      return i;
    }));
  }

  @Test
  public static void checkPrime() {
    Prime.stream().skip(199).limit(1).forEach(System.out::println);
  }
}
