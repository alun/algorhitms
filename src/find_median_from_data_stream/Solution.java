package find_median_from_data_stream;

import java.util.PriorityQueue;

public class Solution {

}

class MedianFinder {

  PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a);
  PriorityQueue<Integer> right = new PriorityQueue<>();

  /** initialize your data structure here. */
  public MedianFinder() {

  }

  public void addNum(int num) {
    if (left.size() == 0 || num < left.peek()) {
      left.add(num);
      if (left.size() > right.size() + 1) {
        right.add(left.poll());
      }
      return;
    }
    right.add(num);
    if (right.size() > left.size() + 1) {
      left.add(right.poll());
    }
  }

  public double findMedian() {
    if (right.size() == 0 && left.size() == 0) {
      return 0;
    }
    if (left.size() == right.size()) {
      return (left.peek() + right.peek()) / 2.0;
    }
    if (left.size() > right.size()) {
      return left.peek();
    }
    if (right.size() > left.size()) {
      return right.peek();
    }
    return 0;
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
