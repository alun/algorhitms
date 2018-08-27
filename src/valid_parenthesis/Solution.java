package valid_parenthesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> opened = new Stack<>();

        Map<Character, Character> brackets = new HashMap<>();
        brackets.put('(', ')');
        brackets.put('{', '}');
        brackets.put('[', ']');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (brackets.containsKey(c)) opened.add(c);
            else if (opened.size() == 0 || brackets.get(opened.peek()) != c) return false;
            else opened.pop();
        }

        return opened.size() == 0;
    }
}
