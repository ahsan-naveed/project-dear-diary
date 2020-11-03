package com.agiledeveloper;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
  private Map<String, Integer> scores = new HashMap<>() {
    {
      put("Jack", 12);
      put("Jill", 15);
      put("Tom", 11);
      put("Darla", 15);
      put("TOM", 11);
      put("Raj", 15);
      put("Nguyen", 11);
    }
  };

  @Test
  void scoresCount() {
    assertAll(
      () -> assertEquals(1, Util.countScores(scores).get(12))
      , () -> assertEquals(3, Util.countScores(scores).get(11))
      , () -> assertEquals(3, Util.countScores(scores).get(15))
    );
  }

  @Test
  void numberOfLetters() {
    assertAll(
      () -> assertTrue(Util.numberOfLetters(scores).get(12).contains(4))
      , () -> assertTrue(Util.numberOfLetters(scores).get(15).contains(5))
      , () -> assertTrue(Util.numberOfLetters(scores).get(15).contains(4))
      , () -> assertTrue(Util.numberOfLetters(scores).get(11).contains(6))
      , () -> assertTrue(Util.numberOfLetters(scores).get(11).contains(3))
    );
  }

  @Test
  void maxNumberOfLetters() {
    assertAll(
      () -> assertEquals(4, (int) Util.maxNumberOfLetters(scores).get(12))
      , () -> assertEquals(5, (int) Util.maxNumberOfLetters(scores).get(15))
      , () -> assertEquals(6, (int) Util.maxNumberOfLetters(scores).get(11))
    );
  }
}
