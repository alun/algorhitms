package multiply_strings;

class Solution {

    int getDigit(String s, int pos) {
        pos = s.length() - 1 - pos;
        if (pos < 0) return 0;
        else return s.charAt(pos) - '0';
    }

    String add(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int carryOn = 0;
        for (int i = 0; i < Math.max(s1.length() ,s2.length()); i++) {
            int sum = getDigit(s1, i) + getDigit(s2, i) + carryOn;
            carryOn = sum / 10;
            sum %= 10;
            sb.insert(0, (char)('0' + sum));
        }
        if (carryOn > 0) sb.insert(0, (char)('0' + carryOn));
        return sb.toString();
    }

    String multByDigit(String s, int digit, int padding) {
        StringBuilder sb = new StringBuilder();
        int carryOn = 0;
        for (int i = 0; i < s.length(); i++) {
            int mult = getDigit(s, i) * digit + carryOn;
            carryOn = mult / 10;
            mult %= 10;
            sb.insert(0, (char)('0' + mult));
        }
        if (carryOn > 0) sb.insert(0, (char)('0' + carryOn));
        while (padding > 0) {
            sb.append('0');
            padding--;
        }
        return sb.toString();
    }

    String multiply(String s1, String s2) {
        String result = "0";
        for (int i = 0; i < s1.length(); i++) {
            result = add(result, multByDigit(s2, getDigit(s1, i), i));
        }
        result = result.replaceFirst("^0+", "");
        return result.length() == 0 ? "0" : result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().multiply("6", "501"));
    }
}
