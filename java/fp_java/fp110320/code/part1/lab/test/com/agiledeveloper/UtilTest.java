package com.agiledeveloper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
  @Test
  void isPrime() {
    assertAll(
      () -> assertFalse(Util.isPrime(-2)),
      () -> assertFalse(Util.isPrime(0)),
      () -> assertFalse(Util.isPrime(1)),
      () -> assertTrue(Util.isPrime(2)),
      () -> assertTrue(Util.isPrime(3)),
      () -> assertFalse(Util.isPrime(4)),
      () -> assertTrue(Util.isPrime(5)),
      () -> assertTrue(Util.isPrime(7)),
      () -> assertFalse(Util.isPrime(9)),
      () -> assertTrue(Util.isPrime(11))
    );
  }

  @Test
  void countWordsInFile() {
    String fileName = "./src/com/agiledeveloper/util.java";

    assertAll(
      () -> assertEquals(0, Util.countWordsInFile(fileName, "nope")),
      () -> assertEquals(1, Util.countWordsInFile(fileName, "isPrime")),
      () -> assertEquals(2, Util.countWordsInFile(fileName, "public"))
    );
  }
}
