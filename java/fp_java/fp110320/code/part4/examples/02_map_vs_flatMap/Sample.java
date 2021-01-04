import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    
    //Suppose we have a one-to-one function: given an input we get an output
    
    //a map is a useful tool to transform data using a one-to-one function
    
    List<Integer> result1 = numbers.stream()
      .map(e -> e + 1)
      .collect(toList());  
    
    //Stream<T>.map(F11) ==> Stream<R>
    
    System.out.println(result1);
    
    //Suppose we have a one-to-many function: given an input we get multiple values in the output
    
    //if we use a map with a one-to-many function, then the result is a collection of collection
    
    List<List<Integer>> result2 = numbers.stream()
      .map(e -> Arrays.asList(e - 1, e + 1))
      .collect(toList());
    
    //Stream<T>.map(F1Listn) ===> Stream<List<R>>
    System.out.println(result2);
    
    //Suppose you start with a list and function that is one-to-many, but you want to
    //end with a list and not a list of list, then...
    List<Integer> result3 = numbers.stream()
      .flatMap(e -> Arrays.asList(e - 1, e + 1).stream())
      .collect(toList());
    
    System.out.println(result3);
  }
}