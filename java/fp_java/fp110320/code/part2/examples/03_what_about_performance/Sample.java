import java.util.*;

public class Sample {  
  public static boolean isGT(int number) {
    return number > 3;
  }

  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  public static int doubleIt(int number) {
    return number * 2;
  }
  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);
    
    //find the double of the first number that is > 3 and is even
    
    //imperative
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
    
    //functional
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
