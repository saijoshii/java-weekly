import java.util.Scanner;
import java.text.NumberFormat;

public class SalaryCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        NumberFormat money = NumberFormat.getCurrencyInstance();

        // Get user input
        System.out.print("Enter the current salary: ");
        double currentSalary = scan.nextDouble();
        scan.nextLine(); // Consume the newline character

        System.out.print("Enter the performance rating (Excellent, Good, or Poor): ");
        String rating = scan.nextLine().trim();

        // Determine raise percentage
        double raisePercentage = switch (rating.toLowerCase()) {
            case "excellent" -> 0.06;
            case "good" -> 0.04;
            case "poor" -> 0.015;
            default -> -1; // Invalid case
        };

        // Validate input and compute new salary
        if (raisePercentage == -1) {
            System.out.println("Invalid rating entered. Please enter 'Excellent', 'Good', or 'Poor'.");
        } else {
            double raise = currentSalary * raisePercentage;
            double newSalary = currentSalary + raise;

            // Display results
            System.out.println("\nCurrent Salary:       " + money.format(currentSalary));
            System.out.println("Amount of your raise: " + money.format(raise));
            System.out.println("Your new salary:      " + money.format(newSalary));
        }
        scan.close();
    }
}
