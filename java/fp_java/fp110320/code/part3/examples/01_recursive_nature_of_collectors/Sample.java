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
    System.out.println(
      createPeople().stream()
        //.collect(groupingBy(Person::getName)));
        //.collect(groupingBy(Person::getName, mapping(Person::getAge, toList()))));
        .collect(groupingBy(Person::getName, mapping(Person::getAge, 
            filtering(age -> age > 30, toList())))));
        
        //CollectorCreator(..., CollectorCreator(..., CollectorCreator(....)))
  }
}