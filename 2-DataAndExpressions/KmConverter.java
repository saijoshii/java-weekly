 import java.util.Scanner;

public class KmConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Convert Miles to Kilometers");
        System.out.print("Please enter the miles: ");
        double miles = scanner.nextDouble();

        double kilometers = convertMilesToKilometers(miles);

        System.out.printf("%.2f Miles is equal to %.2f Kilometers\n", miles, kilometers);
        
        scanner.close();
    }

    // Method to convert miles to kilometers
    public static double convertMilesToKilometers(double miles) {
        final double CONVERSION_FACTOR = 1.60935;
        return miles * CONVERSION_FACTOR;
    }
}
 
