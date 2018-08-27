package substring_with_concatenation_of_all_words;

import java.util.*;
import java.util.stream.Collectors;

class TrieNode {
  Map<Character, TrieNode> children = new HashMap<>();
}

class Trie {
  TrieNode root = new TrieNode();

  public Trie(String text) {
    text = text + "$";
    for (int i = 0; i < text.length(); i++) {
      insert(text.substring(i, text.length()));
    }
  }

  void insert(String text) {
    TrieNode current = root;
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (current.children.containsKey(c)) {
        current = current.children.get(c);
      } else {
        TrieNode next = new TrieNode();
        current.children.put(c, next);
        current = next;
      }
    }
  }

  public Trie subtreeOf(String s) {
    TrieNode current = this.root;
    int i = 0;
    for (i = 0; current != null && i < s.length(); i++) {
      current = current.children.get(s.charAt(i));
    }
    if (current == null) {
      return null;
    }
    Trie result = new Trie("");
    result.root = current;
    return result;
  }

  public List<Integer> leavesDepth() {
    return findLeavesDepth(this.root, 0, new ArrayList<>());
  }

  private List<Integer> findLeavesDepth(TrieNode node, int currentDepth, List<Integer> currentRes) {
    if (node.children.size() == 0) {
      currentRes.add(currentDepth);
    } else for (Map.Entry<Character, TrieNode> e : node.children.entrySet()) {
      findLeavesDepth(e.getValue(), currentDepth + 1, currentRes);
    }
    return currentRes;
  }

}


public class Solution {

  private List<Integer> findSubstringIn(int depth, Trie t, List<String> words) {

    if (words.size() == 0) {
      if (depth > 0) {
        return t.leavesDepth().stream().map(leafDepth -> depth + leafDepth).collect(Collectors.toList());
      } else {
        return Collections.emptyList();
      }
    }

    List<Integer> res = new ArrayList<>();
    for (String w : words) {
      Trie subtrie = t.subtreeOf(w);
      List<String> rest = new ArrayList<>(words);
      rest.remove(w);
      if (subtrie != null) {
        res.addAll(findSubstringIn(depth + w.length(), subtrie, rest));
      }
    }
    return res;
  }

  public List<Integer> findSubstring(String s, String[] words) {
    Trie trie = new Trie(s);
    return this.findSubstringIn(0, trie, Arrays.asList(words)).stream()
        .map(depth -> s.length() - (depth - 1))
        .distinct()
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.out.println(new Solution().findSubstring(
        "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel",
         new String[]   {"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"}
    ));
  }

}

