package knight_probability_in_chessboard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Point {
  int r;
  int c;

  public Point(int r, int c) {
    this.r = r;
    this.c = c;
  }
}

class Solver {
  private int N;
  private int K;
  public Solver(int N, int K) {
    this.N = N;
    this.K = K;
  }

  List<Point> next(Point p) {
    return Arrays.asList(
        new Point(p.r + 2, p.c - 1),
        new Point(p.r + 2, p.c + 1),
        new Point(p.r - 2, p.c - 1),
        new Point(p.r - 2, p.c + 1),
        new Point(p.r + 1, p.c + 2),
        new Point(p.r - 1, p.c + 2),
        new Point(p.r + 1, p.c - 2),
        new Point(p.r - 1, p.c - 2)
    ).stream().filter(e -> e.r >= 0 && e.r < N && e.c >=0 && e.c < N)
        .collect(Collectors.toList());
  }

  public double probability(Point p) {
    if (K == 0) {
      return 1;
    }

    double [][] probs = new double[N][N];
    int stepsLeft = K;
    int r = 0;
    int c = 0;

    for (r = 0; r < N; r++) {
      for (c = 0; c < N; c++) {
        probs[r][c] = next(new Point(r, c)).size() / 8.0;
      }
    }
    double [][] nextProbs = new double[N][N];
    while (--stepsLeft > 0) {
      for (r = 0; r < N; r++) {
        for (c = 0; c < N; c++) {
          double sum = 0;
          for (Point n : next(new Point(r, c))) {
            sum += probs[n.r][n.c];
          }
          nextProbs[r][c] = sum / 8;
        }
      }
      double [][] tmp = probs;
      probs = nextProbs;
      nextProbs = tmp;
    }

    return probs[p.r][p.c];
  }

}

public class Solution {

  public double knightProbability(int N, int K, int r, int c) {
    Point p = new Point(r, c);
    return new Solver(N, K).probability(p);
  }
}
