public class FirmVacation {
    public static void main(String[] args) {
        Staff personnel = new Staff();
        personnel.payday();
    }
}

class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String getName() {
        return name;
    }
    
    public int getVacationDays() {
        return 10; 
    }
}

class Executive extends Employee {
    public Executive(String name, double salary) {
        super(name, salary);
    }
    
    @Override
    public int getVacationDays() {
        return 20; 
    }
}

class Secretary extends Employee {
    public Secretary(String name, double salary) {
        super(name, salary);
    }
    
    @Override
    public int getVacationDays() {
        return 15; 
    }
}

class Volunteer extends Employee {
    public Volunteer(String name) {
        super(name, 0);
    }
    
    @Override
    public int getVacationDays() {
        return 0; 
    }
}

class Hourly extends Employee {
    public Hourly(String name, double hourlyRate) {
        super(name, hourlyRate * 40 * 52);
    }
    
    @Override
    public int getVacationDays() {
        return 5; 
    }
}

class Staff {
    private Employee[] staffList;
    
    public Staff() {
        staffList = new Employee[4];
        staffList[0] = new Executive("Sai", 75000);
        staffList[1] = new Secretary("Dipsan", 45000);
        staffList[2] = new Volunteer("Virat");
        staffList[3] = new Hourly("Binayak", 20);
    }
    
    public void payday() {
        for (Employee employee : staffList) {
            System.out.println(employee.getName() + 
                " - Salary: $" + employee.getSalary() + 
                ", Vacation: " + employee.getVacationDays() + " days");
        }
    }
}
