/** Payroll account with validated, encapsulated salary and bonus. */
public class PayrollSalaryManager {

    private static final double MAX_PERCENT = 100.0;

    static class PayrollAccount {
        private double basicSalary;
        private double bonus;

        public PayrollAccount(double openingBasicSalary) {
            if (openingBasicSalary < 0) {
                System.out.println("Warning: negative basic salary, starting at 0.");
                this.basicSalary = 0;
            } else {
                this.basicSalary = openingBasicSalary;
            }
        }

        public void creditBonus(double amount) {
            if (amount <= 0) {
                System.out.println("Bonus rejected: amount must be positive");
                return;
            }
            bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }

        public void deductTax(double percent) {
            if (percent < 0 || percent > MAX_PERCENT) {
                System.out.println("Tax rejected: percent must be between 0 and 100");
                return;
            }
            basicSalary -= basicSalary * percent / 100;
            System.out.println("Tax deducted: " + (int) percent + "%");
        }

        public double getNetSalary() {
            return basicSalary + bonus;
        }
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
        account.creditBonus(-1);
        account.deductTax(150);
        new PayrollAccount(-10);
    }
}
