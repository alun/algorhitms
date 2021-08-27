package util;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Edge.DEFAULT;
import static util.Edge.DIGIT;

import org.junit.jupiter.api.Test;

class Edge {
  Function<String, Optional<String>> rule;

  /**
   * The "default" edge - matches all, doesn't affect input
   */
  public static final Edge DEFAULT = new Edge(s -> Optional.of(s));

  /**
   * An edge matching DIGIT character
   */
  public static final Edge DIGIT = new Edge(s ->
      (s.length() > 0 && Character.isDigit(s.charAt(0))) ? Optional.of(s.substring(1)) : Optional.empty()
  );

  /**
   * An edge mathing input beginning with t
   *
   * @param t
   * @return
   */
  public static Edge text(final String t) {
    return new Edge(s ->
        (s.length() > 0 && s.indexOf(t) == 0) ?
            Optional.of(s.substring(t.length())) :
            Optional.empty()
    );
  }

  /**
   * An edge based on rule which returns optional next input or empty if the edge doesn't match
   * current input
   *
   * @param rule function taking the current input and returning an optional processed input, empty if doesn't match
   */
  public Edge(Function<String, Optional<String>> rule) {
    this.rule = rule;
  }
}

public class AutomatonNode {
  private List<Pair<Edge, AutomatonNode>> choices = new ArrayList<>();

  static final AutomatonNode TERMINAL = new AutomatonNode("terminal");

  private String name;

  public AutomatonNode(String name) {
    this.name = name;
  }

  public AutomatonNode link(Edge edge, AutomatonNode node) {
    choices.add(new Pair<>(edge, node));
    return this;
  }

  public AutomatonNode linkSelf(Edge edge) {
    return link(edge, this);
  }

  public static boolean match(String s, AutomatonNode node) {
    if (s.length() == 0 && node.choices.size() == 0) {
      return true;
    }
    boolean matches = false;
    for (Pair<Edge, AutomatonNode> choice : node.choices) {
      final AutomatonNode nextNode = choice.getValue();
      final Optional<String> optionalRestInput = choice.getKey().rule.apply(s);
      matches = matches || optionalRestInput.map(nextInput ->
          match(nextInput, nextNode)
      ).orElse(false);
    }
    return matches;
  }

  private static AutomatonNode makeOneOfEdges(Edge [] edges) {
    AutomatonNode res = new AutomatonNode("oneOf edges");
    for (Edge edge : edges) {
      res.link(edge, TERMINAL);
    }
    return res;
  }

  public static AutomatonNode oneOf(AutomatonNode... nodes) {
    AutomatonNode res = new AutomatonNode("oneOf nodes");
    for (AutomatonNode node : nodes) {
      res.link(DEFAULT, node);
    }
    return res;
  }

  static AutomatonNode oneOf(Edge... edges) {
    return makeOneOfEdges(edges);
  }

  static AutomatonNode oneOf(String... inputs) {
    return makeOneOfEdges(
        Arrays.stream(inputs).map(Edge::text).toArray(Edge[]::new)
    );
  }

  static AutomatonNode single(Edge edge) {
    return new AutomatonNode("single").link(edge, TERMINAL);
  }

  static AutomatonNode single(String s) {
    return single(Edge.text(s));
  }

  static AutomatonNode optional(AutomatonNode node) {
    return new AutomatonNode("optional of node").link(DEFAULT, TERMINAL).link(DEFAULT, node);
  }

  static AutomatonNode optional(Edge edge) {
    return new AutomatonNode("optional of edge").link(DEFAULT, TERMINAL).link(edge, TERMINAL);
  }

  static AutomatonNode optional(String s) {
    return optional(Edge.text(s));
  }

  static AutomatonNode zeroOrMore(Edge edge) {
    return new AutomatonNode("zeroOrMore edges").link(DEFAULT, TERMINAL).linkSelf(edge);
  }

  static AutomatonNode oneOrMore(Edge edge) {
    return new AutomatonNode("oneOrMore edge intro").link(edge,
        new AutomatonNode("oneOrMore edge rest").linkSelf(edge).link(DEFAULT, TERMINAL));
  }

  static AutomatonNode seq(AutomatonNode... nodes) {
    if (nodes.length < 2) {
      throw new IllegalArgumentException("At least 2 nodes required");
    }
    for (int i = nodes.length - 2; i >= 0; i--) {
      AutomatonNode current = nodes[i];
      replaceTerminal(current, nodes[i + 1]);
    }
    return nodes[0];
  }

  @Override
  public String toString() {
    return this.name;
  }

  private static void replaceTerminal(AutomatonNode target, AutomatonNode replacement) {
    replaceTerminal(target, replacement, new HashSet<>());
  }

  private static void replaceTerminal(AutomatonNode target, AutomatonNode replacement, Set<AutomatonNode> discovered) {
    discovered.add(target);
    for (Pair<Edge, AutomatonNode> choice : target.choices) {
      if (discovered.contains(choice.getValue())) {
        continue;
      }
      replaceTerminal(choice.getValue(), replacement, discovered);
    }
    target.choices.replaceAll(pair -> {
      if (pair.getValue() == TERMINAL) {
        return new Pair<>(pair.getKey(), replacement);
      } else {
        return pair;
      }
    });
  }

  @Test
  public static void digitsParser() {
    // example implementing following regexp
    // ^(-|\\+)?(\\d*\\.?\\d+|\\d+\\.?\\d*)(e(\\+|-)?\\d+)?d?$

    final AutomatonNode number = seq(
        optional(oneOf("+", "-")),
        oneOf(
            seq(zeroOrMore(DIGIT), optional("."), oneOrMore(DIGIT)),
            seq(oneOrMore(DIGIT), optional("."), zeroOrMore(DIGIT))
        ),
        optional(
            seq(single("e"),
                optional(oneOf("+", "-")),
                oneOrMore(DIGIT))),
        optional("d"));

    assertTrue(match("+12.3e+13d", number));
    assertFalse(match("+12.3e+13fd", number));
  }

}


