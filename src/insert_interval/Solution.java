package insert_interval;

import util.Interval;

import java.util.List;
import java.util.ListIterator;

class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    ListIterator<Interval> it = intervals.listIterator();
    int insertPos = -1;
    while (it.hasNext()) {
      Interval next = it.next();
      boolean overlaps =
          (next.end >= newInterval.start && next.start <= newInterval.end) ||
              (newInterval.end >= next.start && newInterval.start <= next.end);
      if (overlaps) {
        newInterval.start = Math.min(newInterval.start, next.start);
        newInterval.end = Math.max(newInterval.end, next.end);
        it.remove();
      } else if (newInterval.end < next.start) {
        insertPos = it.previousIndex();
        break;
      }
    }
    if (insertPos != -1) {
      intervals.add(insertPos, newInterval);
    } else {
      intervals.add(newInterval);
    }

    return intervals;
  }
}
