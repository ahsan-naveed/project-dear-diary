import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
      
    List<Integer> doubleOfEvenOnly = 
      numbers.stream()
        .filter(e -> e % 2 == 0)
        .collect(toList());
    
    System.out.println(doubleOfEvenOnly);
    
    //collect is a special form of reduce
    //so are min, max, sum, ...
  }
}