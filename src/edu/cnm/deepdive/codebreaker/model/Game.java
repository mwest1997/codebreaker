package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

  private static final String BAD_GUESS_PATTERN_FORMAT = "^.*[^%s].*$";
  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String badGuessPattern;

  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    badGuessPattern = String.format(BAD_GUESS_PATTERN_FORMAT, pool);
  }

  public Code getCode() {
    return code;
  }

  public List<Guess> getGuesses() {
    return Collections.unmodifiableList(guesses);
  }

  public String getPool() {
    return pool;
  }

  public int getLength() {
    return length;
  }

  public Guess guess(String text) {
    if (text.length() != length) {
      throw new IllegalArgumentException();
    }
    if (text.matches(badGuessPattern)) {
      throw new IllegalArgumentException();
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;
  }

}
