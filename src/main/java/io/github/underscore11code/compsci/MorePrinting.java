package io.github.underscore11code.compsci;

// Unit 1 Exercises 1 & 2

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MorePrinting implements Runnable {
  private static final Random random = new Random();

  @Override
  public void run() {
    System.out.println("Card + Box combined");

    BingoCard card = new BingoCard();
    printStringList(new Boxer(card.prettyPrint()).out());

    System.out.println("\nJust Card");
    printStringList(card.prettyPrint());

    System.out.println("\nAnd a randomly sized box because I can");
    int x = random.nextInt(16);
    int y = random.nextInt(16);
    System.out.println("Interior X size = " + x + " (+ 2 padding spaces), interior Y size = " + y);

    List<String> tmp = new ArrayList<>();
    for (int yi = 0; yi < y; yi++) {
      tmp.add(" ".repeat(x));
    }
    printStringList(new Boxer(tmp).out());
  }

  private static class BingoCard {
    public static final int ROWS = 5;
    public static final int COLUMNS = 5;

    List<List<Integer>> cardData = new ArrayList<>();

    public BingoCard() {
      for (int r = 0; r < ROWS; r++) {
        List<Integer> intList = new ArrayList<>();
        cardData.add(intList);
        for (int c = 0; c < COLUMNS; c++) {
          intList.add(generateNumber());
        }
      }
    }

    private int generateNumber() {
      while (true) { // Loop until found good number
        int i = random.nextInt(75);
        boolean taken = false;
        for (List<Integer> rowData : cardData) { // Loop over existing data...
          if (rowData.contains(i)) { // ...and see if the number is used already
            taken = true;
            break;
          }
        }
        if (!taken) return i + 1; // add 1 so it's 1-indexed instead of 0-indexed
      }
    }

    public List<String> prettyPrint() {
      List<String> out = new ArrayList<>();
      out.add("B  I  N  G  O");

      for (List<Integer> rows : cardData) {
        StringBuilder sb = new StringBuilder();

        for (Integer row : rows) {
          String tmp = row.toString();
          sb.append(tmp.length() == 1 ? tmp + " " : tmp).append(" ");
        }

        out.add(sb.toString());
      }

      return out;
    }
  }

  private static class Boxer {
    private List<String> out;

    public Boxer(List<String> content) {
      // Calculate the length of the longest line
      int longestLength = 0;
      for (String s : content) {
        if (s.length() > longestLength) longestLength = s.length();
      }
      out = new ArrayList<>(content);

      String header = "*".repeat(longestLength + 4);

      // Wrap each line with * * w/ appropiate padding
      for (int i = 0; i < content.size(); i++) {
        String tmp = out.get(i);
        tmp = tmp + " ".repeat(longestLength - tmp.length());
        tmp = "* " + tmp + " *";
        out.set(i, tmp);
      }

      // Insert header and footer
      out.add(0, header);
      out.add(header);
    }

    public static Boxer of(String content) {
      List<String> tmp = new ArrayList<>();
      tmp.add(content);
      return new Boxer(tmp);
    }

    public List<String> out() {
      return this.out;
    }
  }

  public static void printStringList(List<String> stringList) {
    for (String s : stringList) {
      System.out.println(s);
    }
  }
}
