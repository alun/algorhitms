package util;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtil {

  static <A, B> Stream<Pair<A, B>> zip(Stream<A> as, Stream<B> bs) {
    Iterator<A> i1 = as.iterator();
    Iterator<B> i2 = bs.iterator();
    Iterable<Pair<A, B>> i = () -> new Iterator<Pair<A, B>>() {
      public boolean hasNext() {
        return i1.hasNext() && i2.hasNext();
      }

      public Pair<A, B> next() {
        return new Pair<A, B>(i1.next(), i2.next());
      }
    };
    return StreamSupport.stream(i.spliterator(), false);
  }

}
