import java.util.Scanner;

public class SphereCalculations {
    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the radius of the sphere
        System.out.print("Enter the radius of the sphere: ");
        double radius = scanner.nextDouble();

        // Define the constant for Pi
        final double PI = Math.PI;

        // Calculate the volume of the sphere using the formula: Volume = (4/3) * π * r^3
        double volume = (4.0 / 3.0) * PI * Math.pow(radius, 3);

        // Calculate the surface area of the sphere using the formula: Surface Area = 4 * π * r^2
        double surfaceArea = 4 * PI * Math.pow(radius, 2);

        // Print the results, formatted to 4 decimal places
        System.out.printf("Volume of the sphere: %.4f\n", volume);
        System.out.printf("Surface area of the sphere: %.4f\n", surfaceArea);

        scanner.close();
    }
}
