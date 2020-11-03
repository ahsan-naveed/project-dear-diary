import java.util.*;

public class Sample {  
  public static void main(String[] args) {
    //The past of Java: imperative style + object-oriented style  
    //Now in Java:  imperative style + object-oriented style + functional style
    
    //imperative style: tell "what" to do and also "how" to do it
    
    List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
    
    //is Nemo there?
    boolean found = false;
    
    //for(int i = 0; i < names.size(); ...) //how
    for(String name: names) { //how
      //if(name == "Nemo") //how not
      if(name.equals("Nemo")) { //how
        found = true;
        break;  //how
      }
    }
    
    if(found) {
      System.out.println("Nemo found");
    } else {
      System.out.println("Nemo not found");
    }
  }
}