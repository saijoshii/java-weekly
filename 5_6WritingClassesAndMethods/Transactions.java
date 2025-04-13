public class Transactions {
    public static void main(String[] args) {
        Account acc1 = new Account("Sai Joshi", "A1234", 1000.0);
        Account acc2 = new Account("Dipsan Thapa", "B5678");
        
        acc2.deposit(500.0);
        acc2.withdraw(200.0);
        
        System.out.println(acc1);
        System.out.println(acc2);
    }
}
