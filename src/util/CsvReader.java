package util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class CsvReader {

  protected Pattern fieldPattern = Pattern.compile("(?:'(.*?)')|(?:\"(.*?)\")|([^,]+)");

  public Stream<List<String>> read(String fileName) {
    try {
      return read(new FileInputStream(new File(fileName)));
    } catch (IOException e) {
      System.err.println(e);
    }
    return Stream.empty();
  }

  public Stream<List<String>> read(InputStream is) {
    final Stream<String> lines = new BufferedReader(new InputStreamReader(is)).lines();
    return lines.map(it -> {
      System.out.println("cosuming " + it);
      return it;
    }).map(this::split);
  }

  List<String> split(String line) {
    Matcher m = this.fieldPattern.matcher(line);
    List<String> result = new ArrayList<>();
    while (m.find()) {
      int i = 1;
      while (m.group(i) == null && i < m.groupCount()) i++;
      result.add(m.group(i));
    }
    return result;
  }

  public static void main(String[] args) {
//    try {
//      InputStream is = new URL("https://ya.ru").openStream();
//      System.out.println(new BufferedReader(new InputStreamReader(is)).lines().collect(joining("\n")));
//    } catch (IOException e) {
//      System.err.println(e);
//    }
//    try (Stream<List<String>> stream = new CsvReader().read("test.csv")) {
//      System.out.println(stream.skip(1).limit(1).collect(Collectors.toList()));
//    }
    Object o = new Object();
    synchronized (o) {
      o.notify();
    }
    try {
      synchronized (o) {
        o.wait(4000);
        System.out.println("Hello");
      }
    } catch (InterruptedException e) {
      System.err.println(e);
    }
  }

}
