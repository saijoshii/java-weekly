import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random generator = new Random();

        // Define choices
        String[] choices = {"R", "P", "S"};

        // Get player's choice
        System.out.print("Enter your choice (R, P, or S): ");
        String personPlay = scan.nextLine().toUpperCase();

        // Validate input
        if (!personPlay.equals("R") && !personPlay.equals("P") && !personPlay.equals("S")) {
            System.out.println("Invalid input, please enter 'R', 'P', or 'S'.");
            return;
        }

        // Generate computer's choice
        String computerPlay = choices[generator.nextInt(3)];
        System.out.println("Computer chose: " + computerPlay);

        // Determine the result using a cleaner approach
        if (personPlay.equals(computerPlay)) {
            System.out.println("IT IS A TIE!");
        } else if ((personPlay.equals("R") && computerPlay.equals("S")) ||
                   (personPlay.equals("P") && computerPlay.equals("R")) ||
                   (personPlay.equals("S") && computerPlay.equals("P"))) {
            System.out.println("You Won!");
        } else {
            System.out.println("You Lost!");
        }
    scan.close();
    }

}
