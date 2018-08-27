package roman_to_integer;

class Solution {

    private int romanDigit(Character roman) {
        switch (roman) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        }
        throw new IllegalArgumentException("Unknown roman '" + roman + "'");
    }

    int romanToInt(String roman) {
        int result = 0;
        int last = 0;
        for (int i = 0; i < roman.length(); i++) {
            int cur = romanDigit(roman.charAt(i));
            if (cur > last) result -= 2 * last;
            result += cur;
            last = cur;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("CIX"));
    }
}

