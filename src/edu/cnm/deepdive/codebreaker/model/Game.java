package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

  private static final String GOOD_CHARACTER_PATTERN_FORMAT = "[%s]";
  private static final String ILLEGAL_LENGTH_MESSAGE =
      "Invalid guess length: code length is %d; guess length is %d.";
  private static final String ILLEGAL_CHARACTER_MESSAGE =
      "Guess includes invalid characters: pool is \"%s\"; guess included=\"%s\".";

  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String goodCharacterPattern;

  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    goodCharacterPattern = String.format(GOOD_CHARACTER_PATTERN_FORMAT, pool);
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

  public int getGuessCount() {
    return guesses.size();
  }

  public Guess guess(String text)
      throws IllegalGuessLengthException, IllegalGuessCharacterException{
    if (text.length() != length) {
      throw new IllegalGuessLengthException(
          String.format(ILLEGAL_LENGTH_MESSAGE, length, text.length()));
    }
    String badCharacters = text.replaceAll(goodCharacterPattern, "");
    if (!badCharacters.isEmpty()) {
      throw new IllegalGuessCharacterException(
          String.format(ILLEGAL_CHARACTER_MESSAGE, pool, badCharacters));
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;
  }

  public void restart() {
    guesses.clear();
  }

}
