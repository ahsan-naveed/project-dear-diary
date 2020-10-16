import java.util.*;
import java.util.stream.Stream;
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

        List<String> slicedMenu1 = 
            specialMenu
                .stream()
                .takeWhile(d -> d.getCalories() < 320)
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(slicedMenu1);

        List<String> slicedMenu2 = 
            specialMenu
                .stream()
                .dropWhile(d -> d.getCalories() < 320)
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(slicedMenu2);
    }
}