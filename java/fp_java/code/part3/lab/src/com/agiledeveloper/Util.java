package com.agiledeveloper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

interface Util {
  public static Map<Integer, Long> countScores(Map<String, Integer> scores) {
    Map<Integer, Long> byScores = new HashMap<>();
    for(String name : scores.keySet()) {
      int score = scores.get(name);

      long count = 0;
      if(byScores.containsKey(score))
        count = byScores.get(score);

      byScores.put(score, count + 1);
    }

    return byScores;
  }

  public static Map<Integer, Set<Integer>> numberOfLetters(Map<String, Integer> scores) {
    Map<Integer, Set<Integer>> byScores = new HashMap<>();
    for(String name : scores.keySet()) {
      int score = scores.get(name);

      Set<Integer> letters = new HashSet<>();
      if(byScores.containsKey(score))
        letters = byScores.get(score);

      letters.add(name.length());
      byScores.put(score, letters);
    }

    return byScores;
  }

  public static Map<Integer, Integer> maxNumberOfLetters(Map<String, Integer> scores) {
    Map<Integer, Integer> byScores = new HashMap<>();
    for(String name : scores.keySet()) {
      int score = scores.get(name);

      int maxLength = 0;
      if(byScores.containsKey(score))
        maxLength = byScores.get(score);

      maxLength = maxLength < name.length() ? name.length() : maxLength;
      byScores.put(score, maxLength);
    }

    return byScores;
  }
}
