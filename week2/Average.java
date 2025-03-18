import java.util.Scanner;

public class Average
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        // get three values from user
        System.out.print("Please enter first integers: ");
        double val1 = scan.nextDouble();
        System.out.print("please enter second integers: ");
        double val2 = scan.nextDouble();
        System.out.print("please enter second integers: "); 
        double val3 = scan.nextDouble();

        // compute the average using a separate method
        double average = computeAverage(val1, val2, val3);

        // print the average
        System.out.println("The average is: " + average);

        scan.close();
    }

    // Method to compute the average
    public static double computeAverage(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }
}
