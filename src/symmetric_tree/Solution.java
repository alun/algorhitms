package symmetric_tree;

import util.TreeNode;

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

  static private class Trees {
    TreeNode t1;
    TreeNode t2;

    static public Trees of(TreeNode t1, TreeNode t2) {
      Trees ts = new Trees();
      ts.t1 = t1;
      ts.t2 = t2;
      return ts;
    }
  }

  private boolean isMirror(Trees trees) {
    Stack<Trees> verify = new Stack<>();
    verify.push(trees);
    while (verify.size() > 0) {
      trees = verify.pop();
      TreeNode t1 = trees.t1;
      TreeNode t2 = trees.t2;

      if (t1 == null && t2 == null) {
        continue;
      }
      if (t1 == null || t2 == null) {
        return false;
      }
      verify.push(Trees.of(t1.left, t2.right));
      verify.push(Trees.of(t1.right, t2.left));
      if (t1.val != t2.val) {
        return false;
      }
    }
    return true;
  }

  public boolean isSymmetric(TreeNode root) {
    return isMirror(Trees.of(root, root));
  }
}
