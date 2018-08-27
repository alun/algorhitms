package implement_magic_dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  private List<String> dictionary = new ArrayList<>();

  /** Build a dictionary through a list of words */
  public void buildDict(String[] dict) {
    this.dictionary.addAll(Arrays.asList(dict));
  }

  private static boolean canMatch(String s1, String s2, int fixes) {
    if (s1.length() != s2.length()) {
      return false;
    }
    if (s1.length() == 0) {
      return fixes == 1;
    }
    return (s1.charAt(0) == s2.charAt(0) && canMatch(s1.substring(1), s2.substring(1), fixes)) ||
        (s1.charAt(0) != s2.charAt(0) && fixes < 1 && canMatch(s1.substring(1), s2.substring(1), fixes + 1));
  }

  /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
  public boolean search(String word) {
    for (String e : this.dictionary) {
      if (canMatch(e, word, 0)) {
        return true;
      }
    }
    return false;
  }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

