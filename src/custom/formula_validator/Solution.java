package custom.formula_validator;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

enum SymbolType {
  DIGIT, OP, OPEN_PAR, CLOSE_PAR, BEGINNING;

  static Optional<SymbolType> getType(String s, int idx) {

    char c = (char) s.codePointAt(idx);
    if (Character.isDigit(c)) return Optional.of(SymbolType.DIGIT);
    if (c == '+' || c == '-') return Optional.of(SymbolType.OP);
    if (c == '(') return Optional.of(SymbolType.OPEN_PAR);
    if (c == ')') return Optional.of(SymbolType.CLOSE_PAR);

    return Optional.empty();
  }
}

/**
 * The problem - create a formula validator capable to say if
 * a given expression is valid. The expression can contain numbers
 * arithmetic ops + and - and brackets - (). For expression to be
 * valid brackets should be balanced and operations could be in either
 * unary of binary form. But not used together
 *   1+-2 not valid
 *   1+(-2) is valid
 */
public class Solution {

  boolean isValid(String formula) {
    SymbolType prev = SymbolType.BEGINNING;
    int parBalance = 0;

    int i = 0;
    while (i < formula.length()) {
      Optional<SymbolType> opt = SymbolType.getType(formula, i);
      if (!opt.isPresent()) return false;
      SymbolType type = opt.get();
      switch (type) {
        case BEGINNING:
          break;
        case DIGIT:
          if (!List.of(
              SymbolType.BEGINNING,
              SymbolType.DIGIT,
              SymbolType.OPEN_PAR,
              SymbolType.OP
          ).contains(prev)) {
            return false;
          }
          break;
        case OP:
          if (!List.of(
              SymbolType.BEGINNING,
              SymbolType.DIGIT,
              SymbolType.CLOSE_PAR,
              SymbolType.OPEN_PAR
          ).contains(prev)) {
            return false;
          }
          break;
        case OPEN_PAR:
          if (!List.of(
              SymbolType.BEGINNING,
              SymbolType.OP,
              SymbolType.OPEN_PAR
          ).contains(prev)) {
            return false;
          }
          parBalance++;
          break;
        case CLOSE_PAR:
          if (!List.of(
              SymbolType.DIGIT,
              SymbolType.CLOSE_PAR
          ).contains(prev)) {
            return false;
          }
          parBalance--;
          break;
      }
      prev = type;
      i += 1;
    }

    if (!List.of(
        SymbolType.DIGIT,
        SymbolType.CLOSE_PAR,
        SymbolType.BEGINNING
    ).contains(prev)) {
      return false;
    }

    return parBalance == 0;
  }

  @Test
  void checkResult() {

    List.of(
        "1+2-(3+43)",
        "1+2-(-3+43)",
        "1+2-((3+43))",
        "-1+2-(-3+43)",
        "1+(-2)"
    ).forEach(f -> assertTrue(isValid(f)));

    List.of(
        "1+2-(3+43))",
        "1+2-((3+43)",
        "1+2-(3+-43)",
        "1+-2"
    ).forEach(f -> assertFalse(isValid(f)));
  }

}

