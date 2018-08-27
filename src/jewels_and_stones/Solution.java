package jewels_and_stones;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/jewels-and-stones/description/
public class Solution {
    public int numJewelsInStones(String J, String S) {
        Map<Integer, Integer> stonesCount = new HashMap<Integer, Integer>();
        S.chars().forEach(stone -> {
            Integer count = stonesCount.get(stone);
            stonesCount.put(stone, count == null ? 1 : count + 1);
        });
        return J.chars()
                .map(stone -> stonesCount.getOrDefault(stone, 0))
                .reduce(Math::addExact)
                .orElse(0);
    }
}
