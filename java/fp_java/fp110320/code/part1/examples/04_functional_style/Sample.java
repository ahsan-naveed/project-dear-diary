import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    //functional: declarative style + higher-order functions

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    //find the total of double of even numbers
    
    //imperative style
    int result = 0;
    
    for(int e: numbers) {
      if(e % 2 == 0) {
        result += e * 2;
      }
    }
    
    System.out.println(result);

    //mapToInt is a function that receives another function (lambda)
    //mapToInt is a higher-order function
    
    //mapToInt simply tells to transform every single element in the collection using the given function
    //the iteration is on auto pilot - the how is taken care we focus on what
       
    //functional style
    System.out.println(
      numbers.stream() //internal iterator
        .filter(e -> e % 2 == 0)
        .mapToInt(e -> e * 2)//: what and not how: declarative + higher-order function == functional
        .sum()); //what and not how: declarative
    
    //filter is also a higher-order function
  }
}