import java.util.*;
import static java.util.stream.Collectors.*;

public class Sample {  
  public static List<Person> createPeople() {
    return List.of(
	    new Person("Sara", 20),
	    new Person("Sara", 22),
	    new Person("Bob", 20),
	    new Person("Paula", 32),
	    new Person("Paul", 32),
	    new Person("Jack", 3),
	    new Person("Jack", 72),
	    new Person("Jill", 11)
	  );
  }
  
  public static void main(String[] args) {
    //Create a Map of names and age of people with that name
    
    //doing this in the imperative style is verbose, boring, hard to parallelize, hard to read...
    
    //functional
    
    //Map<String, List<Integer>>
    System.out.println(
      createPeople().stream()
        .collect(groupingBy(Person::getName, mapping(Person::getAge, toList()))));
  }
}