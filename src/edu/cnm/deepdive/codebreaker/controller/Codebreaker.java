package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import edu.cnm.deepdive.codebreaker.model.Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.SecureRandom;

public class Codebreaker {

  private static final String CHARACTER_POOL = "ROYGBIV";
  private static final int CODE_LENGTH = 4;

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
