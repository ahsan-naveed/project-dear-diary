
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class fpij {
    private static void printArr(List<String> arr) {
        arr.forEach(System.out::println);
    }
    public static void main(String[] args) {
        final List<String> friends = Arrays.asList(
            "Brian",
            "Nate",
            "Neal",
            "Raju",
            "Sara",
            "Scott"
        );
        printArr(friends);
        final List<String> uppercaseNames = new ArrayList<String>();
        for (String f : friends) {
            uppercaseNames.add(f.toUpperCase());
        }
        friends.forEach(String::toUpperCase);
        printArr(uppercaseNames);
    }
}