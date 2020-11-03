import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    
    numbers.stream() //Stream<Integer>
      .map(e -> e * 2.0) //Stream<Double> - function  takes an Integer and returns a Double
      .forEach(System.out::println);
    
    //map iterates automatically over elements in a stream, but only lazily
    //applies the given function to the element and creates a resulting stream
    
    //map takes a function Function<T, R>
    //Stream<T>.map(Function<T, R>) ====> Stream<R>  
    
    //map will give as many elements in the output as the number of elements in the input
  }
}