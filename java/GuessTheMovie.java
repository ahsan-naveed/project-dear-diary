import java.util.*;
import java.io.*;
import java.nio.file.*;
import static java.util.stream.Collectors.*;

public class GuessTheMovie {
    private static List<String> initMovies(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).collect(toList());
    }

    private static String getRandomMovie(List<String> movies) {
        Random seed = new Random();
        int randIdx = seed.nextInt(movies.size());

        return movies.get(randIdx);
    }

    private static String updateAtIndex(String str, char ch, int index) {
        char[] chars = str.toCharArray();
        chars[index] = ch;
        
        return String.valueOf(chars);
    }

    public static void main(String[] args) throws IOException {
        List<String> movies = initMovies("movies.txt");
        
        Scanner scanner = new Scanner(System.in);
        
        String randomMovie = getRandomMovie(movies);
        String answer = randomMovie;
        String guess = "_".repeat(randomMovie.length());
        
        int pointsLeft = 10;
        int wrongGuesses = 0;
        
        System.out.printf("You are guessing: %s\n", guess);
        System.out.println("You have guessed (0) wrong letters:");
        
        while (pointsLeft > 0 && randomMovie.matches(".*[a-zA-Z]+.*")) {
            System.out.print("Guess a letter:");

            // will take only the first character as letter
            char letter = scanner.next().charAt(0);
            int letterIdx = randomMovie.indexOf(letter);

            if ( letterIdx > -1) {
                randomMovie = updateAtIndex(randomMovie, '_', letterIdx);
                guess = updateAtIndex(guess, letter, letterIdx);
            } else {
                pointsLeft -= 1;
                wrongGuesses += 1;
            }

            System.out.printf("You are guessing: %s\n", guess);
            System.out.printf("You have guessed (%d) wrong letters:\n", wrongGuesses);
        }
        
        boolean didWin = !randomMovie.matches(".*[a-zA-Z]+.*") && pointsLeft > 0;

        if (didWin) {
            System.out.println("You Win!");
            System.out.printf("You have guessed '%s' correctly.\n", answer);
        } else {
            System.out.println("You Lost...");
            System.out.printf("You were guessing %s.\n", answer);
        }

        scanner.close();
        return;
    }   
    
}