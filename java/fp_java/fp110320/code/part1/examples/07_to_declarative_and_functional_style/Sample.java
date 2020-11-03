import java.util.*;
import java.util.stream.IntStream;

public class Sample {  
  public static boolean isPrime(int number) {
//    boolean divisible = false;
//    
//    for(int i = 2; i < number; i++) {
//      if(number % i == 0) {
//        divisible = true;
//        break;
//      }
//    }
//    
//    return number > 1 && !divisible;
    
    //think declaratively first, then program functionally
    //a number is prime if, given the range of number 2 to number - 1,
    //no index in that range divides the given number.
    
    //Translate English into code
    
    return number > 1 &&
      IntStream.range(2, number)
        .noneMatch(index -> number % index == 0);
  }
  
  public static void main(String[] args) {
    for(int i = 1; i < 10; i++) {
      System.out.println(i + "---" + isPrime(i));
    }  
  }
}