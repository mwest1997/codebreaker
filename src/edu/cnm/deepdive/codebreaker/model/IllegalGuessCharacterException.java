package edu.cnm.deepdive.codebreaker.model;

/**
 * This class does not let the user type in an illegal character to prevent the app from crashing.
 */
public class IllegalGuessCharacterException extends IllegalArgumentException {

  public IllegalGuessCharacterException() {
  }

  public IllegalGuessCharacterException(String message) {
    super(message);
  }

  public IllegalGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessCharacterException(Throwable cause) {
    super(cause);
  }

}
