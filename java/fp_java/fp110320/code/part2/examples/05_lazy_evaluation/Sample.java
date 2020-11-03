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
    
    numbers.stream()
      .filter(Sample::isGT)
      .filter(Sample::isEven)
      .map(Sample::doubleIt)
      //.findFirst()
      ;
    
    System.out.println("DONE");
  }
}

//What the charm of OOP? polymorphism

//polymorphism is top OOP as lazy evaluation is to FP

//functional programming == functional composition + lazy evaluation
 