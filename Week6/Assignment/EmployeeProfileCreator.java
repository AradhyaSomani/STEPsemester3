/** Permanent employees and interns created with chained constructors. */
public class EmployeeProfileCreator {

    static class Employee {
        private final String empId;
        private final String empName;
        private final double salary;
        private boolean isIntern;

        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        public Employee(String empId, String empName) {
            this(empId, empName, 0);              // interns start with no salary
            this.isIntern = true;
        }

        void printProfile() {
            System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
        }
    }

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000);
        Employee intern = new Employee("E-102", "Arjun");
        permanent.printProfile();
        intern.printProfile();
    }
}
