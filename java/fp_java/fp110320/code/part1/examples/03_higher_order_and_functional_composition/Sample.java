import java.util.*;
import java.util.function.Function;

public class Sample {  
  public static void printInfo(int number, String msg, Function<Integer, Integer> func) {
    System.out.println(number + " " + msg + ": " + func.apply(number));
  }
  
  public static void main(String[] args) {
    //functions
    //we pass objects to functions
    //we create objects within functions
    //we return objects from functions
    
    //higher-order functions  
    //we pass functions to functions
    //we create functions within functions
    //we return functions from functions
    
    //in addition to doing object decomposition we can also do functional decomposition
    
    Function<Integer, Integer> inc = e -> e + 1;
    
    printInfo(5, "incremented", inc);
    printInfo(6, "incremented", inc);
    
    Function<Integer, Integer> doubled = e -> e * 2;
    
    printInfo(5, "incremented", doubled);
    printInfo(6, "incremented", doubled);
    
    printInfo(5, "incremented and doubled", inc.andThen(doubled));
    printInfo(6, "incremented and doubled", inc.andThen(doubled));
  }
}