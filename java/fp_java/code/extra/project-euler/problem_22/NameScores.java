import java.util.Collections;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class NameScores {
    
    public static void main(String[] args) throws IOException {
        String[] lines = Files.readAllLines(Paths.get("names.txt")).get(0).split(",");
        List<String> names = new ArrayList<String>();
        
        names.addAll(List.of(lines));
        Collections.sort(names);

        Integer totalScore = 0;
        for (int i = 0; i < names.size(); i++) {
            totalScore += names.get(i).chars().filter(c -> c != '"').reduce(0, (a, b) -> a + b - 64) * (i + 1);
        }

        System.out.println(totalScore);
    }
}