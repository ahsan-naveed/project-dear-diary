import java.lang.ModuleLayer.Controller;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Dishes {
    static public class Dish {
       private final String name;
       private final boolean vegetarian;
       private final int calories;
       private final Type type;
       
       public Dish(String name, boolean vegetarian, int calories, Type type) {
           this.name = name;
           this.vegetarian = vegetarian;
           this.calories = calories;
           this.type = type;
       }

       public String getName() {
           return name;
       }

       public boolean isVegetarian() {
           return vegetarian;
       }

       public int getCalories() {
           return calories;
       }

       public Type getType() {
           return type;
       }

       @Override
       public String toString() {
           return name;
       }

       public enum Type { MEAT, FISH, OTHER }
    }

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<String> threeHighCaloryDishes = 
            menu
                .stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloryDishes);

        // filtering
        List<String> highCaloryDishes =
            menu
                .stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(highCaloryDishes);

        List<Dish> specialMenu = Arrays.asList(
            new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH), 
            new Dish("rice", true, 350, Dish.Type.OTHER),  
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER)
        );

        // slicing
        List<String> slicedMenu1 = 
            specialMenu
                .stream()
                .takeWhile(d -> d.getCalories() < 320)
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(slicedMenu1);

        // dropWhile & takeWhile
        List<String> slicedMenu2 = 
            specialMenu
                .stream()
                .dropWhile(d -> d.getCalories() < 320)
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(slicedMenu2);

        // skip & limit -> truncating
        List<String> firstTwoMeats = 
            menu
                .stream()
                .filter(d -> Dish.Type.MEAT.equals(d.getType()))
                .limit(2)
                .map(Dish::getName)
                .collect(Collectors.toList());
        
        System.out.println(firstTwoMeats);

        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueChars = 
            words
                .stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // Flattens each generated stream into a single stream
                .distinct()
                .collect(Collectors.toList());
        
        System.out.println(uniqueChars);

        // map
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = 
            numbers
                .stream()
                .map(x -> x*x)
                .collect(Collectors.toList());

        System.out.println(squares);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        // flatMap
        list1
            .stream()
            .flatMap(x ->
                list2
                    .stream()
                    .filter(y -> (x + y) % 3 == 0)
                    .map(y -> Arrays.asList(x, y))
            )
            .forEach(System.out::println);
        
        // allMatch, noneMatch, anyMatch, findFirst, & findAny
        // anyMatch: method returns a boolean and is therefore a terminal operation
        
        // findAny
        menu
            .stream()
            .filter(Dish::isVegetarian)
            .findAny()
            .ifPresent(System.out::println);
        
        // findFirst
        menu
            .stream()
            .filter(Dish::isVegetarian)
            .findFirst()
            .ifPresent(System.out::println);

        // print max(numbers)
        System.out.println(
            numbers
                .stream()
                .reduce(Integer.MIN_VALUE, Integer::max)
        );

        // print number of dishes
        // map-reduce can be easily parallelized
        System.out.println(
            menu
                .stream()
                .count()
        );
    }
}