import java.util.Scanner;

class Person {
    String firstName;
    String lastName;
    String postalCode;

    public Person(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public String toString() {
        return firstName + " " + lastName + " - " + postalCode;
    }
}

public class PostCodeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person[] people = new Person[25];
        int count = 0;

        System.out.println("Enter up to 25 entries (firstName<tab>lastName<tab>postalCode):");
        System.out.println("Type 'done' to finish early.");

        while (count < 25) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = line.split("\t");

            if (parts.length != 3) {
                System.out.println("Invalid input. Please enter 3 parts separated by a tab.");
                continue;
            }

            String firstName = parts[0];
            String lastName = parts[1];
            String postalCode = parts[2];

            people[count] = new Person(firstName, lastName, postalCode);
            count++;
        }

        System.out.println("\nList of People:");
        for (int i = 0; i < count; i++) {
            System.out.println(people[i]);
        }

        scanner.close();
    }
}