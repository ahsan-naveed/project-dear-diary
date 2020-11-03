package com.agiledeveloper;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
  private Map<String, Integer> scores = new HashMap<>();
  private Map<Integer, Set<String>> expected = new HashMap<>();

  @Test
  void groupByScoresEmptyMap() {
    assertEquals(expected, Util.groupByScores(scores));
  }

  @Test
  void groupByScoresWithOneScore() {
    scores.put("Jill", 15);

    expected.put(15, new HashSet<>(Arrays.asList("Jill")));

    assertEquals(expected, Util.groupByScores(scores));
  }

  @Test
  void groupByScoresWithTwoScores() {
    scores.put("Jill", 15);
    scores.put("Jack", 12);

    expected.put(15, new HashSet<>(Arrays.asList("Jill")));
    expected.put(12, new HashSet<>(Arrays.asList("Jack")));

    assertEquals(expected, Util.groupByScores(scores));
  }

  @Test
  void groupByScoresWithThreeScoresWithOneTied() {
    scores.put("Pam", 12);
    scores.put("Jill", 15);
    scores.put("Jack", 12);

    expected.put(15, new HashSet<>(Arrays.asList("Jill")));
    expected.put(12, new HashSet<>(Arrays.asList("Jack", "Pam")));

    assertEquals(expected, Util.groupByScores(scores));
  }

  @Test
  void groupByScoresWithMultipleScores() {
    scores.put("Pam", 12);
    scores.put("Jill", 15);
    scores.put("Jack", 12);
    scores.put("Tom", 11);
    scores.put("Darla", 15);
    scores.put("Raj", 15);
    scores.put("Nguyen", 11);

    expected.put(11, new HashSet<>(Arrays.asList("Tom", "Nguyen")));
    expected.put(12, new HashSet<>(Arrays.asList("Pam", "Jack")));
    expected.put(15, new HashSet<>(Arrays.asList("Jill", "Darla", "Raj")));

    assertEquals(expected, Util.groupByScores(scores));
  }
}
