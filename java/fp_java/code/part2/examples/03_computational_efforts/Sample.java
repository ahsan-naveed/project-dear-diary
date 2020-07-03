import java.util.*;

public class Sample {  
  public static boolean isGT(int n) {
    System.out.println("isGT called for " + n);
    return n > 4;
  }
  
  public static int doubleIt(int n) {
    System.out.println("doubleIt called for " + n);
    return n * 2;
  }
  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //imagine this is not a list of 10 elements but a very large list
    
    //give the list, find the double of the first value greater than 4
    
    Integer result = null;
    
    for(int e: numbers) {
      if(isGT(e)) {
        result = doubleIt(e);
        break;
      }
    }
    
    System.out.println("result is " + result);
    //how much work was done in the imperative code for a very large list with
    //the inital part as above.
    
    //6 function calls
    
    System.out.println("----");
    
    //functional style
    System.out.println(
      numbers.stream()
        .filter(e -> isGT(e))
        .map(e -> doubleIt(e))
        .findFirst()
        .orElse(0));
    
    //6 functional calls
    
    //Streams are fundamentally lazy
    
    //they have two kinds of functions: intermedate and terminal functions
    //intermedate functions do not do any work until terminal functions are called.
    
    //Streams do not run a function for each element. Instead
    //streams run a collection of functions, for one element at a time (sequtially)
    //but only as needed.
  }
}
