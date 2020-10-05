package edu.cnm.deepdive.codebreaker.model;

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
