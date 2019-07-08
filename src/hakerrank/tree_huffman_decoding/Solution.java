package hakerrank.tree_huffman_decoding;
// https://www.hackerrank.com/challenges/tree-huffman-decoding/problem

class Node {
  public int frequency; // the frequency of this tree
  public char data;
  public Node left, right;
}

public class Solution {

  void decode(String s, Node root) {
    Node current = root;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') current = current.left;
      else if (s.charAt(i) == '1') current = current.right;
      else throw new IllegalArgumentException("Bad input at " + i);
      if (current.data != 0) {
        sb.append(current.data);
        current = root;
      }
    }
    System.out.println(sb);
  }

}
