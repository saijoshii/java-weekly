public class Account {
    private final String name;
    private final String accountNumber;
    private double balance;

    public Account(String name, String accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(String name, String accountNumber) {
        this(name, accountNumber, 0.0);
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) Balance: $%.2f", name, accountNumber, balance);
    }
}
