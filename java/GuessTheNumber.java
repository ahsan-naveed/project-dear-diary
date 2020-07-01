import java.util.Random;
import java.util.Scanner;

public class GuessTheMovie {
    private static int getRandNumber() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    public static void main(String[] args) {
        System.out.println("I have randomly choosen a number between [1, 100]");
        System.out.println("Try to guess it.");
        System.out.println("You have 10 guess(es) left:");

        int guessesLeft = 10;
        Scanner scanner = new Scanner(System.in);
        int randNumber = getRandNumber();
        int guess = Integer.parseInt(scanner.nextLine());

        while (guessesLeft > 0) {
            if (randNumber < guess) {
                System.out.printf("It's smaller than %d\n", guess);
            } else if (randNumber > guess) {
                System.out.printf("It's bigger than %d\n", guess);
            } else {
                System.out.println("CORRECT ... YOU WIN!");
                return;
            }

            guessesLeft -= 1;
            System.out.printf("You have %d guess(es) left:\n", guessesLeft);
            guess = Integer.parseInt(scanner.nextLine());
        }
    }
}