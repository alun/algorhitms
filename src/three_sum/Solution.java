package three_sum; // 3-sum



import com.sun.xml.internal.xsom.impl.Ref;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ListNode<T> {
  private T value;
  private ListNode next;
  private int size;
  int getSize() {
    return this.size;
  }
  T getValue() {
    return this.value;
  }
  ListNode getNext() {
    return this.next;
  }
  ListNode(T value) {
    this(value, null);
  }
  ListNode(T value, ListNode<T> next) {
    this.value = value;
    this.next = next;
    this.size = 1 + ((next == null) ? 0 : next.size);
  }
  List<T> toList() {
    ListNode<T> current = this;
    List<T> res = new ArrayList<>(this.size);
    while (current != null) {
      res.add(current.value);
      current = current.next;
    }
    return res;
  }
}

class Element {
  private int value;
  int pos;

  public int getValue() {
    return value;
  }

  public Element(int value, int pos) {
    this.value = value;
    this.pos = pos;
  }
}

public class Solution {

  Stream<List<Element>> pairs(int [] nums, int start, ListNode<Element> head) {
    if (head != null && head.getSize() == 2) {
      List<Element> pair = head.toList();
      pair.sort(Comparator.comparing(Element::getValue));
      return Stream.of(pair);
    }
    return IntStream.range(start, nums.length).boxed().flatMap(i ->
        pairs(nums, i + 1, new ListNode<>(new Element(nums[i], i), head))
    ).distinct();
  }
  public List<List<Integer>> threeSum(int[] nums) {
    Map<Integer, Set<Element>> complements = new HashMap<>();

    IntStream.range(0, nums.length).forEach(i -> {
      int complement = 0 - nums[i];
      Set<Element> set = complements.get(complement);
      if (set == null) {
        set = new HashSet<>();
        complements.put(complement, set);
      }
      set.add(new Element(nums[i], i));
    });

    return pairs(nums, 0, null)
        .map(pair -> {
          Element first = pair.get(0);
          Element second = pair.get(1);
          Set<Element> comps = complements.get(first.getValue() + second.getValue());
          return Optional.ofNullable(comps).flatMap(v ->
            v.stream()
                .filter(e -> e.pos != first.pos && e.pos != second.pos)
                .findAny().map(e -> Arrays.asList(first.getValue(), second.getValue(), e.getValue()))
          );
        })
        .filter(e -> e.isPresent())
        .map(Optional::get)
        .map(list -> {
          list.sort(Comparator.comparing(Integer::intValue));
          return list;
        })
        .distinct()
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.out.println(new Solution().threeSum(new int [] {-1, 0, 1, 2, -1, -4}));
  }
}


