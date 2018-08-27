package binary_tree_tilt;

import util.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

  private int sumTree(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return node.val + sumTree(node.left) + sumTree(node.right);
  }

  public int findTilt(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftSum = sumTree(root.left);
    int rightSum = sumTree(root.right);
    int nodeTilt = Math.abs(leftSum - rightSum);
    return nodeTilt + findTilt(root.left) + findTilt(root.right);
  }
}
