package rotate_image;

class Solution {

  public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int r = 0; r < n / 2; r++) {
      for (int t = 0; t < n - 2 * r - 1; t++) {
        int row = r;
        int col = r + t;
        int val = matrix[row][col];
        for (int k = 0; k < 4; k++) {
          int nr = col;
          int nc = n - row - 1;
          int nval = matrix[nr][nc];
          matrix[nr][nc] = val;
          val = nval;
          row = nr;
          col = nc;
        }
      }
    }
  }
}