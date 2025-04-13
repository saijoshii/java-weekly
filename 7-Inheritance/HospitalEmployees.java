public class HospitalEmployees {

    static class Employee {
        int empNumber;
        String name;

        public Employee(int empNumber, String name) {
            this.empNumber = empNumber;
            this.name = name;
        }

        public void displayDetails() {
            System.out.println("Employee #" + empNumber + ": " + name);
        }
    }

    static class Doctor extends Employee {
        String specialty;

        public Doctor(int empNumber, String name, String specialty) {
            super(empNumber, name);
            this.specialty = specialty;
        }

        public void doCheckup() {
            System.out.println("Doctor #" + empNumber + " (" + name + ") is checking patients in " + specialty);
        }
    }

    static class Nurse extends Employee {
        int patients;

        public Nurse(int empNumber, String name, int patients) {
            super(empNumber, name);
            this.patients = patients;
        }

        public void careForPatients() {
            System.out.println("Nurse #" + empNumber + " (" + name + ") is caring for " + patients + " patients");
        }
    }

    static class Receptionist extends Employee {

        public Receptionist(int empNumber, String name) {
            super(empNumber, name);
        }

        public void answerCalls() {
            System.out.println("Receptionist #" + empNumber + " (" + name + ") is answering phone calls");
        }
    }

    static class Cleaner extends Employee {
        String area;

        public Cleaner(int empNumber, String name, String area) {
            super(empNumber, name);
            this.area = area;
        }

        public void cleanArea() {
            System.out.println("Cleaner #" + empNumber + " (" + name + ") is cleaning " + area);
        }
    }

    public static void main(String[] args) {
        Doctor drBabuRam = new Doctor(101, "Babu Ram", "Cardiology");
        Nurse nurseRhitu = new Nurse(201, "Rhitu", 8);
        Receptionist mrSai = new Receptionist(301, "Sai");
        Cleaner mrsClean = new Cleaner(401, "Mrs. Clean", "Ward 3");

        System.out.println("--- Hospital Day Starts ---");
        drBabuRam.displayDetails();
        drBabuRam.doCheckup();

        nurseRhitu.displayDetails();
        nurseRhitu.careForPatients();

        mrSai.displayDetails();
        mrSai.answerCalls();

        mrsClean.displayDetails();
        mrsClean.cleanArea();
        System.out.println("--- Hospital Day Ends ---");

        System.out.println("\nEmployee List:");
        System.out.println("- Doctor: " + drBabuRam.name);
        System.out.println("- Nurse: " + nurseRhitu.name);
        System.out.println("- Receptionist: " + mrSai.name);
        System.out.println("- Cleaner: " + mrsClean.name);
    }
}
