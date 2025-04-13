import java.util.Random;
import java.util.Scanner;

public class PinEncryption {
    public static void main(String[] args) {
        // Using try-with-resources to automatically close the scanner
        try (Scanner scanner = new Scanner(System.in)) {
            // Ask the user to enter a 4-digit PIN
            System.out.print("Enter a 4-digit PIN to encrypt: ");
            String pin = scanner.nextLine();
            
            // Validate the input to ensure it's a 4-digit number
            if (!pin.matches("\\d{4}")) {
                throw new IllegalArgumentException("PIN must be a four-digit number.");
            }
            
            // Convert the PIN to hexadecimal (upper case)
            String pinHex = Integer.toHexString(Integer.parseInt(pin)).toUpperCase();
            
            // Generate two random numbers greater than 1000 and less than 65536
            Random random = new Random();
            int rand1 = random.nextInt(64536) + 1000;  // Random number between 1000 and 65536
            int rand2 = random.nextInt(64536) + 1000;  // Random number between 1000 and 65536
            
            // Convert the random numbers to hexadecimal (upper case)
            String rand1Hex = Integer.toHexString(rand1).toUpperCase();
            String rand2Hex = Integer.toHexString(rand2).toUpperCase();
            
            // Create the encrypted PIN by sandwiching the PIN hexadecimal between the random hexadecimal values
            String encryptedPin = rand1Hex + pinHex + rand2Hex;
            
            // Print the encrypted PIN
            System.out.println("Encrypted PIN: " + encryptedPin);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
