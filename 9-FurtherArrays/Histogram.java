import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) {
        final int MAX_INPUT = 100;
        int[] inputs = new int[MAX_INPUT];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter integers between 1 and 100 (enter 0 to finish):");
        while (true) {
            int value = scanner.nextInt();
            if (value == 0)
                break;
            if (value < 1 || value > 100) {
                System.out.println("Invalid input. Please enter a number between 1 and 100.");
                continue;
            }
            inputs[count++] = value;
            if (count == MAX_INPUT)
                break;
        }
        int[] frequency = new int[10];
        for (int i = 0; i < count; i++) {
            int index = (inputs[i] - 1) / 10;
            frequency[index]++;
        }
        String[] ranges = new String[10];
        for (int i = 0; i < 10; i++) {
            int lower = i * 10 + 1;
            int upper = (i + 1) * 10;
            ranges[i] = String.format("%2d - %3d | ", lower, upper);
        }
        System.out.println("\nHistogram:");
        for (int i = 0; i < 10; i++) {
            System.out.print(ranges[i]);
            for (int j = 0; j < frequency[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}
