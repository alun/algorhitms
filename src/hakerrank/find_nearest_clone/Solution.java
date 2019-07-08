package hakerrank.find_nearest_clone;
// https://www.hackerrank.com/challenges/find-the-nearest-clone/problem

import java.io.*;
import java.util.*;

public class Solution {

  static final class Graph {
    private Map<Integer, List<Integer>> neigbours = new HashMap<>();

    private void loadFrom(int[] graphFrom, int[] graphTo) {
      for (int i = 0; i < graphFrom.length; i++) {
        Integer key = graphFrom[i];
        List<Integer> list = neigbours.getOrDefault(key, new ArrayList<>());
        list.add(graphTo[i]);
        neigbours.put(key, list);
      }

    }

    Graph(int[] graphFrom, int[] graphTo) {
      loadFrom(graphFrom, graphTo);
      loadFrom(graphTo, graphFrom);
    }

    List<Integer> getNeigbours(int n) {
      return neigbours.get(n);
    }
  }

  static int getStartNode(long[] ids, int val) {
    int i = 0;
    for (i = 0; i < ids.length; i++) {
      if (ids[i] == val) break;
    }
    if (i == ids.length) return -1;
    return i + 1;
  }

  // Complete the findShortest function below.

  /*
   * For the unweighted graph, <name>:
   *
   * 1. The number of nodes is <name>Nodes.
   * 2. The number of edges is <name>Edges.
   * 3. An edge exists between <name>From[i] to <name>To[i].
   *
   */
  static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
    // solve here
    Graph g = new Graph(graphFrom, graphTo);
    int startNode = getStartNode(ids, val);
    if (startNode == -1) return -1;

    Set<Integer> visited = new HashSet<>();
    Deque<List<Integer>> found = new LinkedList<>();
    found.addLast(Arrays.asList(startNode, 0));
    while (found.size() > 0) {
      List<Integer> state = found.removeFirst();
      final int node = state.get(0);
      final int depth = state.get(1);
      for (int nn : g.getNeigbours(node)) {
        if (!visited.contains(nn)) {
          if (ids[nn - 1] == val) {
            return depth + 1;
          } else {
            found.addLast(Arrays.asList(nn, depth + 1));
          }
        }
      }
      visited.add(node);
    }
    return -1;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] graphNodesEdges = scanner.nextLine().split(" ");
    int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
    int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

    int[] graphFrom = new int[graphEdges];
    int[] graphTo = new int[graphEdges];

    for (int i = 0; i < graphEdges; i++) {
      String[] graphFromTo = scanner.nextLine().split(" ");
      graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
      graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
    }

    long[] ids = new long[graphNodes];

    String[] idsItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < graphNodes; i++) {
      long idsItem = Long.parseLong(idsItems[i]);
      ids[i] = idsItem;
    }

    int val = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
