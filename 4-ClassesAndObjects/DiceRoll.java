import java.util.Random;
import java.util.Scanner;

public class DiceRoll {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter number of sides for die 1: ");
        int sidesDie1 = scanner.nextInt();

        System.out.print("Enter number of sides for die 2: ");
        int sidesDie2 = scanner.nextInt();

        int totalDie1 = 0, totalDie2 = 0;

        // Rolling the dice 3 times
        for (int i = 1; i <= 3; i++) {
            int roll1 = random.nextInt(sidesDie1) + 1;
            int roll2 = random.nextInt(sidesDie2) + 1;

            System.out.println("Roll " + i + " - Die 1: " + roll1 + ", Die 2: " + roll2);
            
            totalDie1 += roll1;
            totalDie2 += roll2;
        }

        // Calculating averages
        double avgDie1 = (double) totalDie1 / 3;
        double avgDie2 = (double) totalDie2 / 3;

        // Displaying results
        System.out.printf("Die 1 rolled a total of %d and averaged %.2f per roll.\n", totalDie1, avgDie1);
        System.out.printf("Die 2 rolled a total of %d and averaged %.2f per roll.\n", totalDie2, avgDie2);

        scanner.close();
    }
}
