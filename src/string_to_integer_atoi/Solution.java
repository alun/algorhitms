package string_to_integer_atoi;

public class Solution {
    public int myAtoi(String str) {
        int sign = 1;
        int result = 0;
        int state = 0;
        readLoop:
        for (int i = 0; i < str.length(); i++) {
            char input = str.charAt(i);
            switch (state) {
                case 0:
                    if (input == ' ') continue;
                    else if (input == '+') state = 1;
                    else if (input == '-') {
                        sign = -1;
                        state = 1;
                    } else if ('0' <= input && input <= '9') {
                        result = input - '0';
                        state = 1;
                    } else break readLoop;
                    break;

                case 1:
                if ('0' <= input && input <= '9') {
                    try {
                        result = Math.addExact(Math.multiplyExact(result, 10), sign * (input - '0'));
                    } catch (ArithmeticException e) {
                        result = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                        break readLoop;
                    }
                } else break readLoop;
                break;
            }
        }
        return result;
    }
}
