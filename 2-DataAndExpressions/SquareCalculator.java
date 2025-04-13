import java.util.Scanner;

public class SquareCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter the length of the square's side: ");
        int side = scanner.nextInt();

        // Calculate perimeter and area using methods
        int perimeter = calculatePerimeter(side);
        int area = calculateArea(side);

        // Display the results
        displayResults(perimeter, area);

        scanner.close();
    }
    public static int calculatePerimeter(int side) {
        return 4 * side;
    }

    public static int calculateArea(int side) {
        return side * side;
    }

    public static void displayResults(int perimeter, int area) {
        System.out.println("Perimeter of the square: " + perimeter);
        System.out.println("Area of the square: " + area);
    }
}
