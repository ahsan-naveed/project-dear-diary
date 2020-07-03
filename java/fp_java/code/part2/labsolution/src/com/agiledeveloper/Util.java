package com.agiledeveloper;

import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;

interface Util {
  public static Map<Integer, Set<String>> groupByScores(Map<String, Integer> scores) {
    return scores.keySet()
      .stream()
      .collect(groupingBy(scores::get, toSet()));
  }
}