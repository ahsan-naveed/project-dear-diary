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
      
      //If we want not a list of list, but given a list get one list
      //with a one-to-many function, then we want to perform
      //map and flatten
      
      //what we want is a mapFlatten function.
      //But, mapFlatten is really hard to say, it hurts the jaw
      
      //they called (thankfully) flatMap.
      
      //flatMap is really map and flatten
      
      System.out.println(
        numbers.stream()
          .flatMap(e -> Arrays.asList(e - 1, e + 1).stream())
          .collect(toList()));
          
     //given a one to many function and given a list of values
     //if you want to transform the list to a list of list, use map
     //if you want to transform the list to a list, then use flatMap
      
  }
}