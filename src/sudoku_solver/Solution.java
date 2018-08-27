package sudoku_solver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Game {
    private Map<Integer, Integer> field;
    private List<Set<Integer>> rows;
    private List<Set<Integer>> cols;
    private List<Set<Integer>> squares;
    private int size;
    private int size_sq;

    protected Game(int size) {
        this.size = size;
        this.size_sq = size * size;
        rows = makeSets(size_sq);
        cols = makeSets(size_sq);
        squares = makeSets(size_sq);
        field = new HashMap<>();
    }

    protected Game(Game src) {
        size = src.size;
        size_sq = src.size_sq;
    }

    private List<Set<Integer>> makeSets(int num) {
        List<Set<Integer>> res = new ArrayList<>(num);
        while (num-- > 0) {
            res.add(new HashSet<>());
        }
        return res;
    }

    public Game set(int i, int v) {
// copy on write
        if (!candidates(i).contains(v)) {
            System.out.println(this);
            throw new IllegalArgumentException(v + " can't go into " + i);
        }
        Game res = new Game(this);
        int row = i / size_sq;
        int col = i % size_sq;
        int square = size * (row / size) + col / size;

        res.rows = new ArrayList<>(rows);
        res.cols = new ArrayList<>(cols);
        res.squares = new ArrayList<>(squares);
        res.field = new HashMap<>(field);

        res.rows.set(row, new HashSet(rows.get(row)));
        res.rows.get(row).add(v);

        res.cols.set(col, new HashSet(cols.get(col)));
        res.cols.get(col).add(v);

        res.squares.set(square, new HashSet(squares.get(square)));
        res.squares.get(square).add(v);

        res.field.put(i, v);
        return res;
    }

    public Set<Integer> candidates(int i) {
        int row = i / size_sq;
        int col = i % size_sq;
        int square = size * (row / size) + col / size;

        return IntStream.range(1, size_sq + 1).filter(n ->
                !rows.get(row).contains(n) &&
                        !cols.get(col).contains(n) &&
                        !squares.get(square).contains(n)
        ).mapToObj(n -> (Integer) n).collect(Collectors.toSet());
    }

    public int nextEmpty() {
        return IntStream.range(0, size_sq * size_sq).filter(i ->
                !field.containsKey(i)).findAny().orElse(-1);
    }

    public boolean isSolved() {
        return nextEmpty() == -1;
    }

    public void writeField(char[][] board) {
        for (int row = 0; row < size_sq; row++)
            for (int col = 0; col < size_sq; col++)
                board[row][col] = (char) ('0' + field.get(size * row + col));
    }

    @Override
    public String toString() {
        return IntStream.range(0, size_sq).mapToObj(row ->
                IntStream.range(0, size_sq).mapToObj(col ->
                        new String(new char[]{(char) ('0' + field.getOrDefault(size_sq * row + col, 0))})
                ).collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));
    }
}

public class Solution {
    public Game solve(Game g) {
        Stack<Game> discovered = new Stack<>();
        if (g == null) return null;
        discovered.push(g);
        while (discovered.size() > 0) {
            g = discovered.pop();
            if (g.isSolved()) return g;
            int i = g.nextEmpty();
            for (int v : g.candidates(i)) discovered.add(g.set(i, v));
        }
        return null;
    }

    public void solveSudoku(char[][] board) {
        int size_sq = board.length;
        int size = (int) Math.sqrt(size_sq);
        Game g = new Game(size);

        for (int row = 0; row < size_sq; row++) {
            for (int col = 0; col < size_sq; col++) {
                if (board[row][col] != '.')
                    g = g.set(size_sq * row + col, board[row][col] - '0');
            }
        }

        g = solve(g);
        System.out.println(g);
        if (g != null) g.writeField(board);
    }

    public static void main(String[] args) {
        new Solution().solveSudoku(
                new char[][]{
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                });
    }
}

