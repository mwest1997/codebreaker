package edu.cnm.deepdive.codebreaker.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Implements a code that the user has to try and figure out, and give the user hints after every
 * guess so that the player can figure out the secret code progressively after every guess.
 */
public class Code {

  private final char[] secret;

  /**
   * Defines the random code that the system gives you.
   * @param pool characters allowed in the code.
   * @param length length of code allowed.
   * @param rng randomizes the code the system gives you.
   */
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

  /**
   * Implements a system that reads the guess you gave the system, takes that information and
   * relays to you exactly which guesses you got correct, as well as close.
   */
  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s\", correct: %d, close: %d}";

    private final String text;
    private final int correct;
    private final int close;

    /**
     * Generates the random code that the user has to try and figure out.
     * @param text Gives you an answer back from the system.
     */
    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;

      Map<Character, Set<Integer>> letterMap = getLetterMap(text);

      char[] work = Arrays.copyOf(secret, secret.length);

      for (int i = 0; i < work.length; i++) {
        char letter = work[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
        if (positions.contains(i)) {
          correct++;
          positions.remove(i);
          work[i] = 0;
        }
      }

      for (char letter : work) {
        if (letter != 0) {
          Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
          if (positions.size() > 0) {
            close++;
            Iterator<Integer> iter = positions.iterator();
            iter.next();
            iter.remove();
          }
        }
      }

      this.correct = correct;
      this.close = close;

    }

    private Map<Character, Set<Integer>> getLetterMap(String text) {
      Map<Character, Set<Integer>> letterMap = new HashMap<>();
      char[] letters = text.toCharArray();
      for (int i = 0; i < letters.length; i++) {
        char letter = letters[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, new HashSet<>());
        positions.add(i);
        letterMap.putIfAbsent(letter, positions);
      }
      return letterMap;
    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }

    /**
     * Returns the text of this instance.
     */
    public String getText() {
      return text;
    }

    /**
     * Returns the correct amount of guesses.
     */
    public int getCorrect() {
      return correct;
    }

    /**
     * Returns the amount of guesses that were close, but not correct.
     */
    public int getClose() {
      return close;
    }

  }

}
