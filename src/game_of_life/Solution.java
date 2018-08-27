package game_of_life;

public class Solution {
  private int getNeighbours(int [][] board, int i, int j) {
    int height = board.length;
    int width = board[0].length;
    int count = 0;
    for (int r = i-1; r <= i+1; r++) {
      for (int c = j-1; c <= j+1; c++) {
        if (r < 0 || r >= height || c < 0 || c >= width || (r == i && c == j)) {
          continue;
        }
        if (board[r][c] == 1 || board[r][c] == 3) {
          count += 1;
        }
      }

    }
    return count;
  }

  public void gameOfLife(int[][] board) {
    if (board == null) {
      return;
    }
    int height = board.length;
    if (height == 0) {
      return;
    }
    int width = board[0].length;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int neighbours = getNeighbours(board, r, c);
        if (board[r][c] == 1) {
          if (neighbours < 2 || neighbours > 3) {
            board[r][c] = 3; // just died
          }
        } else if (neighbours == 3) {
          board[r][c] = 2; // just appeared
        }
      }
    }
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        if (board[r][c] == 2) {
          board[r][c] = 1;
        }
        if (board[r][c] == 3) {
          board[r][c] = 0;
        }
      }
    }
  }
}
