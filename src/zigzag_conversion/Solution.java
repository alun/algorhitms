package zigzag_conversion;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ZZChar {
    public int col;
    public int row;
    public char ch;

    public ZZChar(int col, int row, char ch) {
        this.col = col;
        this.row = row;
        this.ch = ch;
    }
}

public class Solution {
    public String convert(final String s, final int numRows) {
        int k = numRows - 1, w = k * 2;
        if (s == null || s.length() < 2 || numRows == 1) return s;

        return IntStream.range(0, s.length())
                .mapToObj(i -> new ZZChar(
                        k * (i / w) + Math.max(0, i % w - k),
                        k - Math.abs(i % w - k),
                        s.charAt(i)
                ))
                .sorted((a, b) -> a.row == b.row ? a.col - b.col : a.row - b.row)
                .map(z -> "" + z.ch)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
    }
}

