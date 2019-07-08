package hakerrank.binary_search_tree_lowest_common_ancestor;
// https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem

import java.util.*;

class Node {
  public int data;
  public Node left, right;
}

public class Solution {

  private static List<Node> findPath(Node root, int v) {
    List<Node> path = new LinkedList<>();
    Deque<Node> found = new LinkedList<>();
    found.add(root);
    while (found.size() > 0) {
      Node cur = found.removeFirst();
      path.add(cur);
      if (v == cur.data) break;
      else if (v > cur.data) {
        if (cur.right != null) found.add(cur.right);
        else return null;
      } else if (v < cur.data) {
        if (cur.left != null) found.add(cur.left);
        else return null;
      }
    }
    return path;
  }

  public static Node lca(Node root, int v1, int v2) {
    List<Node> p1 = findPath(root, v1);
    List<Node> p2 = findPath(root, v2);
    if (p1.size() > p2.size()) {
      List<Node> t = p2;
      p2 = p1;
      p1 = t;
    }
    Set<Node> parents = new HashSet<>(p1);
    p2 = new ArrayList<>(p2);
    for (int i = p2.size() - 1; i >= 0; i--) {
      if (parents.contains(p2.get(i))) return p2.get(i);
    }
    return null;
  }

}