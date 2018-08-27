package regular_expression_matching;

import java.util.List;
import java.util.Stack;

interface RegExp {
    CharSequence parse(CharSequence input);
}

class Single implements RegExp {
    private char c;
    public Single(char c) {
        this.c = c;
    }
    public CharSequence parse(CharSequence input) {
        if (input.length() > 0 && input.charAt(0) == c) {
            return input.subSequence(1, input.length());
        }
        return null;
    }
}

class Any implements RegExp {
    public CharSequence parse(CharSequence input) {
        if (input.length() > 0) {
            return input.subSequence(1, input.length());
        }
        return null;
    }
}

class ZeroOrMore implements RegExp {
    private RegExp child;
    public ZeroOrMore(RegExp child) {
        this.child = child;
    }
    public CharSequence parse(CharSequence input) {
        CharSequence lastInput = input;
        while (input != null && input.length() > 0) {
            input = child.parse(input);
            if (input != null) {
                lastInput = input;
            }
        }
        return lastInput;
    }
}

class Sequence implements RegExp {
    private List<RegExp> children;
    public Sequence(List<RegExp> children) {
        this.children = children;
    }
    public CharSequence parse(CharSequence input) {
        for (int i = 0; i < children.size(); i++) {
            input = children.get(i).parse(input);
            if (input == null) return null;
        }
        return input;
    }
}

public class Solution {
    RegExp build(String re) {
        Stack<RegExp> stack = new Stack<>();
        for (int i = 0; i < re.length(); i++) {
            switch (re.charAt(i)) {
                case '.':
                    stack.push(new Any());
                    break;
                case '*':
                    if (stack.size() > 0)
                        stack.push(new ZeroOrMore(stack.pop()));
                    else throw new IllegalArgumentException("Bad regexp");
                    break;
                default:
                    stack.push(new Single(re.charAt(i)));
            }
        }
        return new Sequence(stack.subList(0, stack.size()));
    }

    boolean isMatch(String input, String re) {
        RegExp regExp = build(re);
        CharSequence result = regExp.parse(input);
        if (result != null && result.equals("")) return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aaa", "a*a"));
    }
}

