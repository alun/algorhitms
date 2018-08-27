package permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) list.add(n);
        return permute(list);
    }

    public List<List<Integer>> permute(List<Integer> nums) {
        if (nums.size() < 2) return Arrays.asList(new ArrayList<Integer>(nums));

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.size(); i++) {
            int first = nums.get(i);
            List<Integer> rest = new ArrayList(nums);
            rest.remove(i);
            for (List<Integer> perm : permute(rest)) {
                perm.add(first);
                result.add(perm);
            }
        }
        return result;
    }
}


