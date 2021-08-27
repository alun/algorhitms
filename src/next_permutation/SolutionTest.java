package next_permutation;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolutionTest {

  private Solution solution;

  @BeforeEach
  void beforeEach() {
    solution = new Solution();
  }

  @Test
  void itFindsASolution() {
    int[] nums = new int[] { 1, 2, 3 };
    solution.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));
  }

}
