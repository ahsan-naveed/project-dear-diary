import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 4, 2, 8, 9, 10);
      
    System.out.println(numbers.stream()
        .filter(e -> e % 2 == 0)
        .collect(toList()));
    
    System.out.println(numbers.stream()
        .filter(e -> e % 2 == 0)
        .collect(toSet()));
    
    System.out.println(
      numbers.stream()
        .takeWhile(e -> e < 4)
        //.collect(toMap(keyFunction, ValueFunction))
        .collect(toMap(e -> e, e -> e *2)));
  }
}