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

}
