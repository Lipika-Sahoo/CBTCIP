import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String play = "yes";

		// while loop to determine if we are going to play the game again
		
		while (play == "yes") {
			Random rand = new Random();
		    int randnum = rand.nextInt(100);
			int guess = -1;
			int tries = 0;

			// while loop to check if the game is over
			while (guess != randnum) {
				System.out.println("Guess a number Between 1 and 100:");
				guess = sc.nextInt();
				tries++;
				if (guess == randnum) {
					System.out.println("Awesome! you guessed the number!");
					System.out.println("it only took you"  + tries +  "guesses!");
					System.out.print("would you like to play again? Yes or No");
				} else if (guess > randnum) {
					System.out.println("your guess is too high,try again.");
				} else {
					System.out.println("your guess is too low,try again.");
				}
			}
		}
		sc.close();

	}

}