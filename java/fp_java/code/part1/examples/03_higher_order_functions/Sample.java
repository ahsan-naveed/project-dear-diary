import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    //functions
    /*
    we may pass an object to a function
    we may create an object in a function
    we may return an object from a function
    */

    //higher-order functions
    /*
    we may pass an function to a function
    we may create an function in a function
    we may return an function from a function
    */

    //functional programming
    /*
    is declarative + higher order functions
    in nature
    */
      
    //example: Thread's constructor is a higher-order function  
    //we are able to pass an anonymous function to the constructor.
    Thread th = new Thread(() -> System.out.println("In another thread"));
    
    th.start();
    
    System.out.println("In main");  
  }
}