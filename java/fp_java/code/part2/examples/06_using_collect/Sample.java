import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    System.out.println(
      numbers.stream()
        .filter(e -> e % 2 == 0)
        .map(e -> e * 2)
        .collect(toList()));
    
    //collect is also a reduce opeeration. Instead of reducing to a single value
    //it reduces the stream to a list.
    
    //what is reduce then?
    //going from a stream to a non-stream
  }
}
