import java.util.Scanner;

public class LLBank {
    private static BankAccount[] accounts = new BankAccount[30];
    private static int numAccounts = 0; 

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nL&L Bank Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Make Deposit");
            System.out.println("3. Make Withdrawal");
            System.out.println("4. Add Interest to All Accounts");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    createAccount(input);
                    break;
                case 2:
                    makeDeposit(input);
                    break;
                case 3:
                    makeWithdrawal(input);
                    break;
                case 4:
                    addInterestToAll();
                    break;
                case 5:
                    viewAllAccounts();
                    break;
                case 6:
                    System.out.println("Thank you for banking with L&L!");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner input) {
        if (numAccounts >= 30) {
            System.out.println("Sorry, bank can only handle 30 accounts.");
            return;
        }

        input.nextLine();
        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        System.out.print("Enter initial deposit: ");
        double amount = input.nextDouble();

        if (amount < 0) {
            System.out.println("Invalid amount. Account not created.");
            return;
        }

        accounts[numAccounts] = new BankAccount(name, amount);
        System.out.println("Account created successfully!");
        System.out.println("Your account number is: " + accounts[numAccounts].getAccountNumber());
        numAccounts++;
    }

    private static void makeDeposit(Scanner input) {
        System.out.print("Enter account number: ");
        int accountNum = input.nextInt();

        int index = findAccountIndex(accountNum);
        if (index == -1) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = input.nextDouble();

        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        accounts[index].deposit(amount);
        System.out.println("Deposit successful. New balance: " + accounts[index].getBalance());
    }

    private static void makeWithdrawal(Scanner input) {
        System.out.print("Enter account number: ");
        int accountNum = input.nextInt();

        int index = findAccountIndex(accountNum);
        if (index == -1) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = input.nextDouble();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (accounts[index].withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + accounts[index].getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }

    private static void addInterestToAll() {
        for (int i = 0; i < numAccounts; i++) {
            accounts[i].addInterest();
        }
        System.out.println("3% interest added to all accounts.");
    }

    private static void viewAllAccounts() {
        System.out.println("\nAll Accounts:");
        for (int i = 0; i < numAccounts; i++) {
            System.out.println(accounts[i]);
        }
    }

    private static int findAccountIndex(int accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return -1;
    }
}

class BankAccount {
    private String customerName;
    private double balance;
    private int accountNumber;
    private static int nextAccountNumber = 1;

    public BankAccount(String name, double initialDeposit) {
        customerName = name;
        balance = initialDeposit;
        accountNumber = nextAccountNumber++;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void addInterest() {
        balance += balance * 0.03;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String toString() {
        return String.format("Account #%d: %s - Balance: $%.2f", accountNumber, customerName, balance);
    }
}
