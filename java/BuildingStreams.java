import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * BuildingStreams
 */
public class BuildingStreams {

    public static void main(String[] args) {
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriplesV2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[] { a, b, Math.sqrt(a * a + b * b) }).filter(t -> t[2] % 1 == 0));

        pythagoreanTriplesV2.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        // Stream from values
        Stream<String> stream = Stream.of("Modern", "Java", "in", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // empty stream: Stream<String> emptyStream = Stream.empty();

        // java 8
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream
            = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        homeValueStream.forEach(System.out::println);

        // java 9
        Stream.ofNullable(System.getProperty("home")).forEach(System.out::println);

        Stream<String> values = Stream.of("config", "home", "user").flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);
    }
}