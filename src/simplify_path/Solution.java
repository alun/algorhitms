package simplify_path;

import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Substring {
  String source;
  int beg;
  int end;

  public Substring(String source, int beg, int end) {
    this.source = source;
    this.beg = beg;
    this.end = end;
  }

  @Override
  public String toString() {
    return source.substring(beg, end);
  }
}

public class Solution {

  public String simplifyPath(String path) {
    if (path == null) {
      return null;
    }
    Pattern nonSlash = Pattern.compile("[^/]+");
    Matcher m = nonSlash.matcher(path);
    Stack<Substring> stack = new Stack<>();
    while (m.find()) {
      String elem = m.group(0);
      if (elem.equals(".")) {
        continue;
      } else if (elem.equals("..")) {
        if (stack.size() > 0) {
          stack.pop();
        }
      } else {
        stack.push(new Substring(path, m.start(), m.end()));
      }
    }
    StringBuilder result = new StringBuilder();
    while (stack.size() > 0) {
      result.insert(0, "/" + stack.pop().toString());
    }

    if (result.length() > 0) {
      return result.toString();
    } else {
      return "/";
    }
  }
}
