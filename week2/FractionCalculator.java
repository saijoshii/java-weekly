 import java.util.Scanner;

public class FractionCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for the numerator and denominator
        System.out.print("Enter the numerator: ");
        int numerator = scanner.nextInt();

        System.out.print("Enter the denominator: ");
        int denominator = scanner.nextInt();

        // Calculate and display the result
        calculateAndDisplayFraction(numerator, denominator);

        scanner.close();
    }

    // Method to calculate and display the decimal equivalent of a fraction
    public static void calculateAndDisplayFraction(int numerator, int denominator) {
        if (denominator != 0) {
            double decimalEquivalent = (double) numerator / denominator;
            System.out.printf("The decimal equivalent of %d/%d is %.4f\n", numerator, denominator, decimalEquivalent);
        } else {
            System.out.println("Error: Denominator cannot be zero.");
        }
    }

}
    

