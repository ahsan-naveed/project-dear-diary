import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class GuessTheMovie {
    private static ArrayList<String> initMovies() {
        ArrayList<String> movies = new ArrayList<>();
        try {
            File file = new File("movies.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                movies.add(fileScanner.nextLine());
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find that file");
        } catch (Exception e) {
            System.out.println(e);
        }
        return movies;
    }

    private static String getRandomMovie(ArrayList<String> movies) {
        Random rand = new Random();
        int randIdx = rand.nextInt(movies.size());
        return movies.get(randIdx);
    }

    private static String updateAtIndex(String str, char ch, int index) {
        char[] chars = str.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        ArrayList<String> movies = initMovies();
        String randomMovie = getRandomMovie(movies);
        System.out.println(randomMovie);
        String guess = "_".repeat(randomMovie.length());
        Scanner scanner = new Scanner(System.in);
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
            System.out.printf("You have guessed '%s' correctly.\n", guess);
        }

        scanner.close();
        return;
    }   
    
}