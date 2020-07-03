import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    //Stream is an internal iterator
    
    numbers.stream()
      .filter(e -> e % 2 == 0)
      .map(e -> e * 2)
      .forEach(e -> System.out.println(e));  
      
    //functional composition
    //a pipeline of function chained together.
    //Like water pipes at home
    
    //    List                vs.              Stream
    // collection of data             pipeline of functions
    //-----> A/C unit ---> Purifier ----> 
    //        map           filter
    
    //Unix pipeline
    // cat stuff.txt | grep "something" | wc -l
  }
}
/*
  Give a collection x1, x2, x3,..., xn
      apply the function f(x) ===> y to get
  y1, y2, y3,..., yn
*/