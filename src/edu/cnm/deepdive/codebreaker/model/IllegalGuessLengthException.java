package edu.cnm.deepdive.codebreaker.model;

/**
 * This class does not let the user input too many or too less characters in so that it matches
 * the length of the code correctly as well as prevents the app from crashing.
 */
public class IllegalGuessLengthException extends IllegalArgumentException {

  public IllegalGuessLengthException() {
  }

  public IllegalGuessLengthException(String message) {
    super(message);
  }

  public IllegalGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessLengthException(Throwable cause) {
    super(cause);
  }
}
