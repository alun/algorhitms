package integer_to_english_words;

public class Solution {

  private String join(String s1, String s2) {
    if (s2.length() == 0) {
      return s1;
    }
    return s1 + " " + s2;
  }

  private String tensToWords(int num) {
    switch (num) {
      case 0:
      case 1:
        return "";
      case 2:
        return "Twenty";
      case 3:
        return "Thirty";
      case 4:
        return "Forty";
      case 5:
        return "Fifty";
      case 6:
        return "Sixty";
      case 7:
        return "Seventy";
      case 8:
        return "Eighty";
      case 9:
        return "Ninety";
    }
    return "";
  }

  private String thousandToWords(int num) {
    switch (num) {
      case 0:
        return "";
      case 1:
        return "One";
      case 2:
        return "Two";
      case 3:
        return "Three";
      case 4:
        return "Four";
      case 5:
        return "Five";
      case 6:
        return "Six";
      case 7:
        return "Seven";
      case 8:
        return "Eight";
      case 9:
        return "Nine";
      case 10:
        return "Ten";
      case 11:
        return "Eleven";
      case 12:
        return "Twelve";
      case 13:
        return "Thirteen";
      case 14:
        return "Fourteen";
      case 15:
        return "Fifteen";
      case 16:
        return "Sixteen";
      case 17:
        return "Seventeen";
      case 18:
        return "Eighteen";
      case 19:
        return "Nineteen";
    }

    if (num < 100) {
      return join(tensToWords(num / 10), thousandToWords(num % 10));
    }

    String hundreds = thousandToWords(num / 100);
    return join(hundreds + " Hundred", thousandToWords(num % 100));

  }

  private String suffix(int order) {
    switch (order) {
      case 0:
        return "";
      case 3:
        return "Thousand";
      case 6:
        return "Million";
      case 9:
        return "Billion";
    }
    return "";
  }

  private String numberToWordsRec(int num, int order) {
    if (num == 0) {
      return "";
    }
    String prefix = numberToWordsRec(num / 1000, 3 + order);
    return join(prefix, (num % 1000 == 0) ? "" : (thousandToWords(num % 1000) + " " + suffix(order)));
  }

  public String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }
    return numberToWordsRec(num, 0).trim();
  }
}
