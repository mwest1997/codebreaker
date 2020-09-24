package edu.cnm.deepdive.codebreaker.model;

import java.util.Random;

public class Code {

  private final char[] secret;

  public Code(String pool, int length, Random rng) {
    secret = new char[length];
    for (int i = 0; i < secret.length; i++) {
      secret[i] = pool.charAt(rng.nextInt(pool.length()));
    }
  }

  @Override
  public String toString() {
    return new String(secret);

  }

  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s\", correct: %d, close: %d}";

    private final String text;
    private final int correct;
    private final int close;

    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;
      for (int i = 0; i < secret.length; i++) {
        char current = secret[i];
        int position = text.indexOf(current);
        if (i == position) {
          correct++;
        } else if (position >= 0) {
          close++;
        }

      }
      this.correct = correct;
      this.close = close;

    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }

    public String getText() {
      return text;
    }

    public int getCorrect() {
      return correct;
    }

    public int getClose() {
      return close;
    }

  }

}
