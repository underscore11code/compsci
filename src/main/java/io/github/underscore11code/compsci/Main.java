package io.github.underscore11code.compsci;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello, World!");

    System.out.println(myInfo());
  }

  private static String myInfo() {
    final StringBuilder sb = new StringBuilder();
    sb.append("meow");
    return sb.toString();
  }
}
