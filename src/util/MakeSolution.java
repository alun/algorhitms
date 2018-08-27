package util;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class MakeSolution {

  private static String getClipboard() {
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    Transferable t = clipboard.getContents(null);
    try {
      return (String) t.getTransferData(DataFlavor.stringFlavor);
    } catch (UnsupportedFlavorException|IOException e) {
      System.err.println(e);
    }
    return "";
  }

  private static String path(String... components) {
    return Arrays.stream(components).collect(joining(File.separator));
  }

  public static void main(String[] args) {
    String pkgName = getClipboard().replaceAll("-", "_");
    System.out.println(pkgName);
    if (pkgName.length() == 0) {
      System.exit(-1);
    }
    String cwd = System.getProperty("user.dir");
    String pkgPath = path("src", pkgName);
    File pkg = new File(cwd, pkgPath);
    pkg.mkdirs();

    try {
      FileWriter writer = new FileWriter(new File(pkg, "Solution.java"));
      writer.write("package " + pkgName + ";\n");
      writer.write("\n");
      writer.write("public class Solution {\n\n}");
      writer.close();
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}
