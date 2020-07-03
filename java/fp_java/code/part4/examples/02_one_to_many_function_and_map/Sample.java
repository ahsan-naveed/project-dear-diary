import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static void main(String[] args) {
      List<Integer> numbers = Arrays.asList(1, 2, 3);
      
      //one-to-many function: e -> number before and number after
      System.out.println(
        numbers.stream()
          .map(e -> Arrays.asList(e - 1, e + 1))
          .collect(toList()));
          
      //one-to-one function
      //Stream<T>.map(f1to1) ==> Stream<R>
      
      //one-to-many function
      //Stream<T>.map(f1ton) ===> Stream<List<R>>
  }
}