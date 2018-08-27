package spiral_matrix_ii;

enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;
}

class Solution {
  void fill(int[][] matrix, int i, int j, int value, int i_min, int i_max, int j_min, int j_max, Direction dir) {
    matrix[j][i] = value;
    System.out.println(value);
    switch (dir) {
      case RIGHT:
        if (i == i_max) {
          if (j < j_max) {
            fill(matrix, i, j + 1, value + 1, i_min, i_max, j_min + 1, j_max, Direction.DOWN);
          }
        } else {
          fill(matrix, i + 1, j, value + 1, i_min, i_max, j_min, j_max, Direction.RIGHT);
        }
        break;
      case DOWN:
        if (j == j_max) {
          if (i > i_min) {
            fill(matrix, i-1, j, value + 1, i_min, i_max-1, j_min, j_max, Direction.LEFT);
          }
        } else {
          fill(matrix, i, j + 1, value + 1, i_min, i_max, j_min, j_max, Direction.DOWN);
        }
        break;
      case LEFT:
        if (i == i_min) {
          if (j > j_min) {
            fill(matrix, i, j - 1, value + 1, i_min, i_max, j_min, j_max - 1, Direction.UP);
          }
        } else {
          fill(matrix, i - 1, j, value + 1, i_min, i_max, j_min, j_max, Direction.LEFT);
        }
        break;
      case UP:
        if (j == j_min) {
          if (i < i_max) {
            fill(matrix, i + 1, j, value + 1, i_min + 1, i_max, j_min, j_max, Direction.RIGHT);
          }
        } else {
          fill(matrix, i, j - 1, value + 1, i_min, i_max, j_min, j_max, Direction.UP);
        }
        break;
      default:
        throw new IllegalArgumentException("Unexpected direction");
    }
  }

  public int[][] generateMatrix(int n) {
    int [][] res = new int [n][n];
    fill(res, 0, 0, 1, 0, n - 1, 0, n - 1, Direction.RIGHT);
    return res;
  }
}


