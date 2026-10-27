/** Piggy bank whose savings change only through deposit and withdraw. */
public class PiggyBank {

    private final String id;
    private int savings;

    public PiggyBank(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id;
        this.savings = 0;
    }

    public String getId() {
        return id;
    }

    public int getSavings() {
        return savings;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: amount must be positive");
            return;
        }
        savings += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0 || amount > savings) {
            System.out.println("Withdraw rejected, savings stays " + savings);
            return;
        }
        savings -= amount;
    }

    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        System.out.println("savings = " + pb.getSavings());
        pb.withdraw(30);
        System.out.println("savings = " + pb.getSavings());
        pb.withdraw(500);
        System.out.println("final savings = " + pb.getSavings());
    }
}
