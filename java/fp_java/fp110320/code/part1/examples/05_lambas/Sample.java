import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    //functions have 4 things
    //name
    //parameter list
    //return type
    //body
    
    //lambda expressions: anonymous function whose return type is inferred
    // (parameter list) -> body

//    Thread th = new Thread(new Runnable() {
//      public void run() { //this is a function, but we, in the past, had to wrap that into an object
//        System.out.println("running...");
//      }
//    });

    Thread th = new Thread(() -> System.out.println("running..."));
    //we can pass function to function (constructor) instead of having to wrap the function
    //into an object and then pass it.
    
    //we passed a lambda expression to the Thread constructor.
    
    th.start();
    
    System.out.println("In main");
  }
}