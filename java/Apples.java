import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Apples {
    
    enum Color {
        GREEN,
        RED
    }

    // program to an interface not an implementation

    // class of algorithms (strategies)
    static public interface ApplePredicate {
        boolean test(Apple apple);
    }

    // strategy pattern

    // strategy I: green apples
    static public class AppleGreenColorPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return Color.GREEN.equals(apple.getColor());
        }
    }

    // strategy II: heavy apples
    static public class AppleHeavyWeightPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    // strategy III: red and heavy apples
    static public class AppleRedAndHeavyPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }

    static public class Apple {
        Color _color;
        Integer _weight;
    
        public Apple(Color color, Integer weight){
            _color = color;
            _weight = weight;
        }
        
        public Color getColor() {
            return _color;
        }

        public Integer getWeight() {
            return _weight;
        }
    }

    static public List<Apple> filterApplesByColor(List<Apple> apples, Color c) {
        return apples
            .stream()
            .filter(a -> a.getColor().equals(c))
            .collect(Collectors.toList());
    }

    // behavior parameterization
    static public List<Apple> filterApples(List<Apple> apples, ApplePredicate p) {
        return apples
            .stream()
            .filter(a -> p.test(a))
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        
        List<Apple> apples = new ArrayList<>();
        
        IntStream.range(0, 20).forEach(i -> {
            if ( i % 2 == 0) {
                apples.add(new Apple(Color.GREEN, i * 25));
            } else {
                apples.add(new Apple(Color.RED, i * 15));
            }
        });
        
        // brittle filtering
        System.out.println(filterApplesByColor(apples, Color.GREEN));

        // flexible filtering
        System.out.println(filterApples(apples, new AppleGreenColorPredicate()));
        System.out.println(filterApples(apples, new AppleHeavyWeightPredicate()));
        System.out.println(filterApples(apples, new AppleRedAndHeavyPredicate()));

        // Note:
        // the behavior of the filterApples method depends on the code we pass to it
        // via the ApplePredicate object. We've parameterized the behavior of the
        // filterApples method
    }   
}