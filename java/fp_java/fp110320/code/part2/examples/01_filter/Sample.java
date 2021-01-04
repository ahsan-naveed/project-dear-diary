import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);  
    
    numbers.stream()
      .filter(e -> e % 2 == 0)
      .forEach(System.out::println);
  }
}

/*
filter takes a predicate

0 <= # of output <= # of input

elements in the output are a subset of elements in the input

*/