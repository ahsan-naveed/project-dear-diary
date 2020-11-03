package com.agiledeveloper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

interface Util {
  public static boolean isPrime(int number) {
    //range goes from start to finish - 1
    //rangeClosed goes from start to finish

    return number > 1 &&
      IntStream.range(2, number)
        .noneMatch(index -> number % index == 0);
  }

  public static long countWordsInFile(String filePath, String searchWord) throws IOException {
    return Files.lines(Paths.get(filePath))
      .filter(line -> line.contains(searchWord))
      .count();
  }
}