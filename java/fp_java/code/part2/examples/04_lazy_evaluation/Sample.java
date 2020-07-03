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

    numbers.stream()
      .filter(e -> isGT(e))
      .map(e -> doubleIt(e))
      //.findFirst() //terminal operation 
      ;

    System.out.println("DONE");
  }
}
