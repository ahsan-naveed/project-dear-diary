import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * BuildingStreams
 */
public class BuildingStreams {

    public static void main(String[] args) {
        Stream<int[]> pythagoreanTriples 
            = IntStream
                .rangeClosed(1, 100).boxed()
                .flatMap(a ->
                    IntStream.rangeClosed(a, 100)
                             .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                             .mapToObj(b ->
                                new int[] { a, b, (int) Math.sqrt(a * a + b * b) }
                             )
                );

        pythagoreanTriples
            .limit(5)
            .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriplesV2
            = IntStream
                .rangeClosed(1, 100).boxed()
                .flatMap(a ->
                    IntStream
                        .rangeClosed(a, 100)
                        .mapToObj(b ->
                            new double[] { a, b, Math.sqrt(a * a + b * b) }
                        ).filter(t -> t[2] % 1 == 0)
                );

        pythagoreanTriplesV2
            .limit(5)
            .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

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

        // for stream of vlaues that may include nullable objects
        Stream<String> values
            = Stream
                .of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        values.forEach(System.out::println);

        // stream from arrays
        System.out.println(Arrays.stream(new int[] { 1, 3, 2 }).sum());

        // stream from files
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords
                = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            // Do nothing
        } finally {
            System.out.println(String.format("Unique words: %d", uniqueWords));
        }

        // streams from functions: creating infinite streams!
        // iterate
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        // Fibonacci tuples series
        Stream
            .iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
            .limit(20)
            .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // Fib series
        Stream
            .iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
            .limit(20)
            .map(t -> t[0])
            .forEach(System.out::println);

        // iterate with predicate
        IntStream
            .iterate(0, n -> n < 100, n -> n + 4)
            .forEach(System.out::println);

        // bad! cuz of side-effect
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream
            .generate(fib)
            .limit(10)
            .forEach(System.out::println);
    }
}