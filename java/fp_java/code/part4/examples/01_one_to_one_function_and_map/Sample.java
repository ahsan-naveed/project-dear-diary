import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static void main(String[] args) {
      List<Integer> numbers = Arrays.asList(1, 2, 3);
      
      //one-to-one function: e -> e * 2
      System.out.println(
        numbers.stream()
          .map(e -> e * 2)
          .collect(toList()));
  }
}