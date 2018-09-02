package set_matrix_zeroes;

import java.util.HashSet;
import java.util.Set;

public class Solution {

  static final int VALUE = 100000;
  public void setZeroes(int[][] matrix) {

    Set<Integer> zeroColsConsidered = new HashSet<>();
    for (int r = 0; r < matrix.length; r++) {
      Set<Integer> zeroCols = new HashSet<>();
      for (int c = 0; c < matrix[r].length; c++) {
        if (matrix[r][c] == 0) {
          zeroCols.add(c);
        }
      }
      zeroCols.removeAll(zeroColsConsidered);
      if (zeroCols.size() > 0) {
        for (int c = 0; c < matrix[r].length; c++) {
          if (matrix[r][c] < VALUE) {
            matrix[r][c] += 2 * VALUE;
          }
        }
        for (int c : zeroCols) {
          for (int r2 = 0; r2 < matrix.length; r2++) {
            if (matrix[r2][c] < VALUE) {
              matrix[r2][c] += 2 * VALUE;
            }
          }
        }

      }
      zeroColsConsidered.addAll(zeroCols);
    }
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[r].length; c++) {
        if (matrix[r][c] > VALUE) {
          matrix[r][c] = 0;
        }
      }
    }
  }
}
