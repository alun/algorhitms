import util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {

  public static void insert(TreeNode root, int value) {
    if (root.val < value) {
      if (root.right != null) {
        insert(root.right, value);
      } else {
        root.right = new TreeNode(value);
      }
    } else {
      if (root.left != null) {
        insert(root.left, value);
      } else {
        root.left = new TreeNode(value);
      }
    }
  }

  public static boolean equals(TreeNode t1, TreeNode t2) {
    return (t1 == null && t2 == null) || (t1.val == t2.val && equals(t1.left, t2.left)
        && equals(t1.right, t2.right));
  }

  public List<TreeNode> generateTrees(int n) {
    List<TreeNode> res = new ArrayList<>();
    for (List<Integer> perm : permutations(n)) {
      TreeNode node = new TreeNode(perm.get(0));
      for (Integer num : perm.subList(1, perm.size())) {
        insert(node, num);
      }
      if (!res.stream().filter(t -> equals(t, node)).findAny().isPresent()) {
        res.add(node);
      }

    }
    return res;
  }

  public static List<List<Integer>> permutations(int n) {
    if (n == 0) {
      return Collections.emptyList();
    }
    if (n == 1) {
      return Arrays.asList(Arrays.asList(1));
    }
    List<List<Integer>> result = new ArrayList<>();
    for (List<Integer> perm : permutations(n - 1)) {
      for (int i = 0; i <= perm.size(); i++) {
        List<Integer> newPerm = new ArrayList<>(perm);
        newPerm.add(i, n);
        result.add(newPerm);
      }
    }
    return result;
  }

}
