import java.util.Scanner;

public class Reverser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        // Splitting sentence into words
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        // Reverse each word
        for (String word : words) {
            result.append(new StringBuilder(word).reverse()).append(" ");
        }

        // Print the output
        System.out.println("Reversed sentence: " + result.toString().trim());

        scanner.close();
    }
}
