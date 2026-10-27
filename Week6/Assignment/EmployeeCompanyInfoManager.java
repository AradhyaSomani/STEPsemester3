/** Shared company name and employee counter kept in static fields. */
public class EmployeeCompanyInfoManager {

    static class Employee {
        private static final String companyName = "Bright Horizon Technologies";
        private static int employeeCount = 0;

        private final String empName;
        private final double salary;

        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        new Employee("Divya", 65000);
        new Employee("Arjun", 0);
        new Employee("Meera", 72000);
        Employee.printCompanyInfo();
    }
}
