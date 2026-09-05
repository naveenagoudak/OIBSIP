import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalRounds = 0;
        int totalScore = 0;

        System.out.println("==========================================");
        System.out.println("   WELCOME TO NUMBER GUESSING GAME!       ");
        System.out.println("==========================================");

        while (playAgain) {
            totalRounds++;
            int numberToGuess = random.nextInt(100) + 1;
            int maxAttempts = 7;
            int attemptsTaken = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + totalRounds + " ---");
            System.out.println("A number has been chosen between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            while (attemptsTaken < maxAttempts) {
                System.out.print("Attempt " + (attemptsTaken + 1) + "/" + maxAttempts + " - Enter your guess: ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer number!");
                    scanner.next();
                    continue;
                }

                int userGuess = scanner.nextInt();
                attemptsTaken++;

                if (userGuess == numberToGuess) {
                    System.out.println("Correct! You guessed the number in " + attemptsTaken + " attempts.");
                    guessedCorrectly = true;
                    int points = (maxAttempts - attemptsTaken + 1) * 10;
                    totalScore += points;
                    System.out.println("Points earned this round: " + points);
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too Low! Try a higher number.");
                } else {
                    System.out.println("Too High! Try a lower number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\nYou Lost! You've used all " + maxAttempts + " attempts.");
                System.out.println("The secret number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }

        System.out.println("\n==========================================");
        System.out.println("               GAME OVER                  ");
        System.out.println("Rounds Played: " + totalRounds);
        System.out.println("Total Score  : " + totalScore);
        System.out.println("Thank you for playing!");
        System.out.println("==========================================");

        scanner.close();
    }
}
