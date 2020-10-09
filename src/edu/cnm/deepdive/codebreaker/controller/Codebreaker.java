package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import edu.cnm.deepdive.codebreaker.model.Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.SecureRandom;

/**
 * Implements a simple console-mode game in which the program generates a secret cond and the user
 * guesses the code. After each guess, the program displays the results of that guess&mdash; namely
 * the number of characters in the correct position, and the number of additional characters that
 * are in the secret code and the guess, but are not in the correct position in the guess.
 *
 * @version 1.0
 * @author Austin West
 */
public class Codebreaker {

  private static final String CHARACTER_POOL = "ROYGBIV";
  private static final int CODE_LENGTH = 4;

  /**
   * Entry point for Codebreaker game. Creates an instance of {@link Game}, and repeatedlt takes
   * user input to submit as a guess, using the {@link Game#guess(String)} method.
   *
   * @param args Command-line arguments (ignored).
   */

  public static void main(String[] args) {
    Game game = new Game(CHARACTER_POOL, CODE_LENGTH, new SecureRandom());
    System.out.printf("Pool: %s. Code length: %d%n", CHARACTER_POOL, CODE_LENGTH);
    boolean correct = false;
    Reader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);
    do {
      try {
        String text = reader.readLine();
        Guess guess = game.guess(text);
        if (guess.getCorrect() == CODE_LENGTH) {
          System.out.printf("Congratulations! The secret code was %s.%n", game.getCode());
          correct = true;
        } else {
          System.out.printf("Correct: %d. Close: %d.%n", guess.getCorrect(), guess.getClose());
        }
      } catch (IOException e) {
        System.out.println("Unable to read input. Program stopped.");
        e.printStackTrace();
        System.exit(1);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } while (!correct);
  }
}
