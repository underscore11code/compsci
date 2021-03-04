package io.github.underscore11code.compsci;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Theatre implements Runnable {
  Set<Sellable> sellables = Set.of(
    new Sellable("burger", 3.25f),
    new Sellable("drink", 1.95f),
    new Sellable("ticket", 12.25f),
    new Sellable("popcorn", 5f)
  );
  private static final Scanner scanner =  new Scanner(System.in);

  @Override
  public void run() {
    Map<Sellable, Integer> amounts = new HashMap<>();
    for (Sellable sellable : sellables) {
      System.out.println("How many " + sellable.displayName() + "s do you want?");
      int count = scanner.nextInt();
      amounts.put(sellable, count);
    }

    float total = 0f;
    for (Map.Entry<Sellable, Integer> entry : amounts.entrySet()) {
      total += entry.getKey().price() * entry.getValue();
    }

    System.out.println("Your total is " + total);
  }

  private static class Sellable {
    private final String displayName;
    private final float price;

    public Sellable(String displayName, float price) {
      this.displayName = displayName;
      this.price = price;
    }

    public String displayName() {
      return this.displayName;
    }

    public float price() {
      return this.price;
    }
  }
}
