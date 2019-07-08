package strong_password_checker;

import java.util.function.Supplier;

public class Solution {

    String remove(String s, int index) {
        return replace(s, index, -1);
    }

    String replace(String s, int index, int c) {
        return s.substring(0, index) + (c == -1 ? "" : Character.toString((char)c)) +
                s.substring(index + 1, s.length());
    }

    String insert(String s, int index, char c) {
        return s.substring(0, index) + c + s.substring(index, s.length());
    }

    int checkAndImprove(String p, int steps) {

        int upperIndex = -1;
        int lowerIndex = -1;
        int digitIndex = -1;
        int tripletIndex = -1;

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.toLowerCase(c) == c && lowerIndex == -1)
                    lowerIndex = i;
                if (Character.toUpperCase(c) == c && upperIndex == -1)
                    upperIndex = i;
            } else if (Character.isDigit(c) && digitIndex == -1)
                digitIndex = i;

            if (i > 1 && p.charAt(i) == p.charAt(i - 1) &&
                    p.charAt(i) == p.charAt(i - 2) &&
                    tripletIndex == -1)
                tripletIndex = i;
        }

        if (p.length() >= 6 && p.length() <= 20 &&
                lowerIndex != -1 && upperIndex != -1 &&
                digitIndex != -1 && tripletIndex == -1) {
            return steps;
        }

        Supplier<Character> randLower = () -> (char) ('a' + ('z' - 'a') * Math.random());
        Supplier<Character> randUpper = () -> (char) ('A' + ('Z' - 'A') * Math.random());
        Supplier<Character> randDigit = () -> (char) ('0' + ('9' - '0') * Math.random());
        char newSymbol =
                lowerIndex == -1 ? randLower.get() :
                        upperIndex == -1 ? randUpper.get() :
                                digitIndex == -1 ? randDigit.get() : randLower.get();

        if (tripletIndex != -1) {
            while (newSymbol == p.charAt(tripletIndex) &&
                    (tripletIndex >= p.length() - 1 || newSymbol == p.charAt(tripletIndex + 1)))
                newSymbol = randLower.get();

            if (p.length() < 6) {
                return checkAndImprove(insert(p, tripletIndex, newSymbol), steps + 1);
            }
            if (p.length() > 20) {
                return Math.min(
                        checkAndImprove(remove(p, tripletIndex), steps + 1),
                        checkAndImprove(replace(p, tripletIndex, newSymbol), steps + 1)
                );
            }
            return checkAndImprove(replace(p, tripletIndex, newSymbol), steps + 1);
        }

        if (p.length() < 6) {
            return checkAndImprove(newSymbol + p, steps + 1);
        }

        if (p.length() > 20) {
            for (int i = 0; i < p.length(); i++)
                if (i != upperIndex && i != lowerIndex && i != digitIndex)
                    // and check that a new triplet is not created
                    return checkAndImprove(remove(p, i), steps + 1);
        }

        if (upperIndex == -1 || lowerIndex == -1 || digitIndex == -1) {
            for (int i = 0; i < p.length(); i++)
                if (i != upperIndex && i != lowerIndex && i != digitIndex)
                    // and check that a new triplet is not created
                    return checkAndImprove(replace(p, i, newSymbol), steps + 1);
        }

        throw new RuntimeException("Unreachable state");
    }

    public int strongPasswordChecker(String s) {
        return checkAndImprove(s, 0);
    }

    public static void main(String[] args) {
//        System.out.println(new Solution.java().strongPasswordChecker("aaa111"));
//        System.out.println(new Solution.java().strongPasswordChecker("aaaaaaaaaaaaaaaaaaaaa"));
//        System.out.println(new Solution.java().strongPasswordChecker("abababababababababaaa"));
//        System.out.println(new Solution.java().strongPasswordChecker("abAbababababababaaa"));
//        System.out.println(new Solution.java().strongPasswordChecker("1Abababcaaaabababababa"));
        System.out.println(new Solution().strongPasswordChecker("..."));

    }
}



