import java.util.*;

public class Sample {  
  public static boolean isGT(int number) {
    System.out.println("isGT called for " + number);
    return number > 3;
  }

  public static boolean isEven(int number) {
    System.out.println("isEven called for " + number);
    return number % 2 == 0;
  }

  public static int doubleIt(int number) {
    System.out.println("doubleIt called for " + number);
    return number * 2;
  }
  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);
    
    //find the double of the first number that is > 3 and is even
    
    System.out.println("imperative");
    Integer result = null;
    
    for(int e : numbers) {
      if(isGT(e) && isEven(e)) {
        result = doubleIt(e);
        break;
      }
    }
    
    //how many units of work did we do in this example, in the above code? 8 units of work
    
    System.out.println(result);

    //how many units of work did we do in this example, in the below code?
    //if we look at it without regard to the semantics of the code and look at only the syntax:
    //21 units of work
    //Oh, my that is totally out of line.
    //But, but, the code is cute...
    //I am out of here.
    
    //Is this really true?
    
    System.out.println("functional");
    System.out.println(
      numbers.stream()
        .filter(Sample::isGT)
        .filter(Sample::isEven)
        .map(Sample::doubleIt)
        .findFirst()
        .orElse(0)
    );
  }
}


/*
          List                                      Stream
       Bucket of data                           Pipeline of functions
       datastructure                            abstraction                                                
                                          Intermediate functions and terminal functions
                                          Intermediate functions do not run until a terminal function is called

Stream does not run a given function for each value.

It fuses the given functions together: combines filter, filter, and map

Then, it run the fused function, but only on demand and mimimaly
*/
