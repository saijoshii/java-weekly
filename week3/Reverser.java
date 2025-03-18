import java.util.Scanner;

public class Reverser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        
        // Using StringBuilder for efficient reversal
        String reversed = new StringBuilder(sentence).reverse().toString();
        
        System.out.println("The reversed string is: " + reversed);
        scanner.close();
    }
}
