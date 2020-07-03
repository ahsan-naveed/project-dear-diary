package com.agiledeveloper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

interface Util {
  public static boolean isPrime(int number) {
    //give a number we want to check if any number between 2 and number - 1 divides it
    return number > 1 && IntStream.range(2, number)
      .noneMatch(i -> number % i == 0);
  }

  public static long countWordsInFile(String filePath, String searchWord) throws IOException {
    //get the lines of text in the file and look in each line for the word
    return Files.lines(Paths.get(filePath))
      .filter(line -> line.contains(searchWord))
      .count();
  }
}