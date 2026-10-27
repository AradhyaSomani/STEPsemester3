/** Locker whose combination can be changed but never read back. */
public class Locker {

    private final int lockerNumber;
    private String code;                    // intentionally no getter

    public Locker(int lockerNumber, String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be empty.");
        }
        this.lockerNumber = lockerNumber;
        this.code = code;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    public boolean changeCode(String currentCode, String newCode) {
        if (!code.equals(currentCode) || newCode == null || newCode.isEmpty()) {
            return false;                   // nothing changes
        }
        code = newCode;
        return true;
    }

    public static void main(String[] args) {
        Locker locker = new Locker(101, "1234");
        System.out.println("changeCode(1234 -> 5678): " + (locker.changeCode("1234", "5678") ? "success" : "rejected"));
        System.out.println("changeCode(0000 -> 9999): " + (locker.changeCode("0000", "9999") ? "success" : "rejected"));
        System.out.println("old code 5678 still works: " + locker.changeCode("5678", "5678"));
    }
}
