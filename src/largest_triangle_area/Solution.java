package largest_triangle_area;

class Solution {

  public double largestTriangleArea(int[][] points) {
    double max = Double.MIN_VALUE;
    for (int i = 0; i < points.length - 2; i++) {
      for (int j = i + 1; j < points.length - 1; j++) {
        for (int k = j + 1; k < points.length; k++) {
          int[] p1 = points[i];
          int[] p2 = points[j];
          int[] p3 = points[k];
          double current = 1. / 2 * Math.abs(
              (p2[0] - p1[0]) * (p3[1] - p1[1]) - // a.x * b.y
                  (p3[0] - p1[0]) * (p2[1] - p1[1])   // b.x * a.y
          );
          if (current > max) {
            max = current;
          }
        }
      }
    }
    return max;
  }

}
