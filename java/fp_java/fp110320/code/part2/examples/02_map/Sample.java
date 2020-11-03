import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);  
    
    numbers.stream()
      .filter(e -> e % 2 == 0)
      .map(e -> e * 2.0) //Stream<Integer> ===> Stream<Double>
      .forEach(System.out::println);
  }
}

/*
map takes a function

x1, x2, x3 ... 
f(xi)

map applies the function for each elemement so we get

yi = f(xi)

y1, y2, y3, ...

# of output == # of input

The type of the output may be different from the type of the input

*/