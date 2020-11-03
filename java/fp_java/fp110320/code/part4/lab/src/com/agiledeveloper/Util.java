package com.agiledeveloper;

import java.util.ArrayList;
import java.util.List;

interface Util {
  public static String getTriple(int m, int n) {
    int a = m * m - n * n;
    int b = 2 * m * n;
    int c = m * m + n * n;

    return String.format("%d %d %d", a, b, c);
  }

  public static List<String> computeTriples(int numberOfValues) {
    List<String> triples = new ArrayList<>();
    int count = 1;

    for(int m = 2; ; m++) {
      for(int n = 1; n < m; n++) {
        triples.add(getTriple(m, n));
        count++;

        if(count > numberOfValues)
          break;
      }

      if(count > numberOfValues)
        break;
    }

    return triples;
  }
}