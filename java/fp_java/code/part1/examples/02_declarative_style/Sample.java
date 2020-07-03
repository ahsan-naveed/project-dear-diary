import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    //In the past Java: imperative style + object-oriented style
    //Now Java: imperative + object-oriented + functional
    
    //imperative: tell what to do and also tell how to do it.
    //declarative: tell what to do and *not* how to do it.
    
    List<String> names = 
      Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
      
    if(names.contains("Nemo"))
      System.out.println("Nemo found");
    else
      System.out.println("Nemo not found");

//    boolean found = false;
//    
//    for(String name: names) {
//      if(name.equals("Nemo")) {
//        found = true;
//        break;
//      }
//    }
//    
//    if(found)
//      System.out.println("Nemo found");
//    else
//      System.out.println("Nemo not found");
  }
}