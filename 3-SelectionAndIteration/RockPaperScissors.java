import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        // Get player's choice
        System.out.print("Enter R for Rock, P for Paper, or S for Scissors: ");
        String playerChoice = scan.nextLine().trim().toUpperCase();

        // Validate input
        if (!(playerChoice.equals("R") || playerChoice.equals("P") || playerChoice.equals("S"))) {
            System.out.println("Invalid choice! Please enter R, P, or S.");
            scan.close();
            return;
        }

        // Generate computer's choice
        int computerChoiceNum = random.nextInt(3); // 0 = Rock, 1 = Paper, 2 = Scissors
        String computerChoice = "";
        
        if (computerChoiceNum == 0) {
            computerChoice = "R";
        } else if (computerChoiceNum == 1) {
            computerChoice = "P";
        } else {
            computerChoice = "S";
        }

        // Display computer's choice
        System.out.println("Computer chose: " + computerChoice);

        // Determine the winner
        if (playerChoice.equals(computerChoice)) {
            System.out.println("It's a tie!");
        } else if ((playerChoice.equals("R") && computerChoice.equals("S")) ||
                   (playerChoice.equals("P") && computerChoice.equals("R")) ||
                   (playerChoice.equals("S") && computerChoice.equals("P"))) {
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");
        }

        scan.close();
    }
}
