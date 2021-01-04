package com.agiledeveloper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

interface Util {
  public static Map<Integer, Long> countScores(Map<String, Integer> scores) {
    return scores.keySet()
      .stream()
      .collect(groupingBy(scores::get, counting()));
  }

  public static Map<Integer, Set<Integer>> numberOfLetters(Map<String, Integer> scores) {
    return scores.keySet()
      .stream()
      .collect(groupingBy(scores::get, mapping(String::length, toSet())));
  }

  public static Map<Integer, Integer> maxNumberOfLetters(Map<String, Integer> scores) {
    return scores.keySet()
      .stream()
      .collect(groupingBy(scores::get,
        collectingAndThen(maxBy(comparing(String::length)), name -> name.orElse("").length())));
  }
}
