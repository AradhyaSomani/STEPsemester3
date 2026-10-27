/** Rates password strength without ever revealing the password. */
public class PasswordChecker {

    private static final int MEDIUM_MIN_LENGTH = 6;
    private static final int STRONG_MIN_LENGTH = 10;

    private final String password;              // no getter

    public PasswordChecker(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }
        this.password = password;
    }

    public String getStrength() {
        if (password.length() < MEDIUM_MIN_LENGTH) return "Weak";
        if (password.length() < STRONG_MIN_LENGTH) return "Medium";
        return "Strong";
    }

    public static void main(String[] args) {
        String[] samples = {"abcd", "abcdefgh", "abcdefghij", "abcdefghijkl"};
        for (String sample : samples) {
            System.out.println(sample.length() + " characters -> " + new PasswordChecker(sample).getStrength());
        }
    }
}
