public class StudentTable {
    public static void main(String[] args) {
        String name1 = "Sai Joshi";
        int lab1 = 40, bonus1 = 10;
        String name2 = "Dipsan Bahadur";
        int lab2 = 40, bonus2 = 8;
        String name3 = "dipendra singh";
        int lab3 = 35, bonus3 = 6;
        String name4 = "kohli";
        int lab4 = 26, bonus4 = 8;
        String name5 = "Louis";
        int lab5 = 45, bonus5 = 5;
        System.out.println("///////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("==\t     Student Points    \t==");
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////////\n");
        System.out.printf("%-15s %-5s %-7s %-5s%n", "Name", "Lab", "Bonus", "Total");
        System.out.printf("%-15s %-5s %-7s %-5s%n", "----", "---", "-----", "-----");
        System.out.printf("%-15s %-5d %-7d %-5d%n", name1, lab1, bonus1, (lab1 + bonus1));
        System.out.printf("%-15s %-5d %-7d %-5d%n", name2, lab2, bonus2, (lab2 + bonus2));
        System.out.printf("%-15s %-5d %-7d %-5d%n", name3, lab3, bonus3, (lab3 + bonus3));
        System.out.printf("%-15s %-5d %-7d %-5d%n", name4, lab4, bonus4, (lab4 + bonus4));
        System.out.printf("%-15s %-5d %-7d %-5d%n", name5, lab5, bonus5, (lab5 + bonus5));
    }
}
