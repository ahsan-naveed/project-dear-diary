import java.util.*;
import java.util.Collections;
import java.io.*;
import java.nio.file.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class NameScroes {
    
    public static void main(String[] args) throws IOException {
        String[] lines = Files.readAllLines(Paths.get("names.txt")).get(0).split(",");
        List<String> names = new ArrayList<String>();
        
        for (String name : lines) {
            names.add(name);
        }

        Collections.sort(names);

        Integer totalScore = 0;
        for (int i = 0; i < names.size(); i++) {
            totalScore += names.get(i).chars().filter(c -> c != '"').reduce(0, (a, b) -> a + b - 64) * (i + 1);
        }

        System.out.println(totalScore);
    }
}