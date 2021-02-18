package io.github.underscore11code.compsci;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomRockPaperScissors implements Runnable {
  private static final Random random = new Random();

  // I mean I am using printf, that's the goal right?

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      // My opponents are Hex Strings :P
      playGame(Integer.toHexString(random.nextInt()));
      System.out.println("\n\n");
    }
  }

  private void playGame(String opponent) {
    System.out.printf("Game with AI %s\n", opponent);
    List<Result> state = new ArrayList<>();
    while (true) {
      Result result = new Result();
      state.add(result);
      System.out.printf("%-8s %-8s %-8s\n", result.h1(), result.h2(), result.resultState());
      int wins = countState(state, ResultState.WIN);
      int losses = countState(state, ResultState.LOSS);
      int ties = countState(state, ResultState.TIE);

      if (wins >= 3 || losses >= 3) {
        System.out.printf("My wins: %s, My losses: %s, Ties: %s. Result: I %s\n", wins, losses, ties, wins > losses ? "won" : "lost");
        return;
      }
    }
  }

  private static int countState(List<Result> state, ResultState stateToCount) {
    int buffer = 0;
    for (Result result : state) {
      if (result.resultState() == stateToCount) buffer++;
    }
    return buffer;
  }

  private enum Hand {
    ROCK,
    PAPER,
    SCISSORS;

    public static Hand random() {
      return values()[random.nextInt(values().length)];
    }
  }

  private static class Result {
    private final Hand h1;
    private final Hand h2;
    private final ResultState resultState;

    public Result() {
      this.h1 = Hand.random();
      this.h2 = Hand.random();
      this.resultState = ResultState.of(h1, h2);
    }

    public Result(Hand h1, Hand h2) {
      this.h1 = h1;
      this.h2 = h2;
      this.resultState = ResultState.of(h1, h2);
    }

    public Hand h1() {
      return this.h1;
    }

    public Hand h2() {
      return this.h2;
    }

    public ResultState resultState() {
      return this.resultState;
    }
  }

  private enum ResultState {
    WIN,
    LOSS,
    TIE;

    public static ResultState of(Hand h1, Hand h2) {
      final int compare = h1.compareTo(h2);

      if (h1 == Hand.SCISSORS && h2 == Hand.ROCK) return LOSS;

      if (compare == 0) return TIE;

      if (compare == 1) return WIN;

      if (compare == -1) return LOSS;

      if (compare == -2) return WIN;

      if (compare == 2) return WIN;

      return null;
    }
  }
}
