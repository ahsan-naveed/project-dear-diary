import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");  
    
    //Are any names 10 characters long
    
    /*
    boolean found = false;
    ....the whole mess...
    
    imperative style code is more verbose, complex, error prone, uses mutability (makes it harder to parallelize)
    
    We familiar with it
    */
    
    /*
    functional style is simpler, declarative, easy to maintain, less error prone,
    easier to parallelize
    
    functional is largely unfamiliar
    
    Please do not confuse familiar with simple
    */
    
    //noneMatch is declarative and functional
    if(names.stream().noneMatch(name -> name.length() == 10)) 
      System.out.println("No one has name with 10 character");
    else
      System.out.println("yep");
  }
}
