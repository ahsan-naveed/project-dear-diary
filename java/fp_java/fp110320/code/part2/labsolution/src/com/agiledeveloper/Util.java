package com.agiledeveloper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

interface Util {
  public static Map<Integer, Set<String>> groupByScores(Map<String, Integer> scores) {
    return scores.keySet()
      .stream()
      //.collect(groupingBy(name -> scores.get(name), toSet()));
      .collect(groupingBy(scores::get, toSet()));
  }
}