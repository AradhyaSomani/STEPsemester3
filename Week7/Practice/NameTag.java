/** Immutable name tag that builds a short nickname from a full name. */
public final class NameTag {

    private final String firstName;
    private final String lastName;

    public NameTag(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("Full name cannot be null.");
        }
        String[] parts = fullName.trim().split(" ");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException("Full name must be 'First Last': " + fullName);
        }
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getNickname() {
        return firstName + " " + lastName.charAt(0) + ".";
    }

    public static void main(String[] args) {
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("getNickname() -> \"" + tag.getNickname() + "\"");
        try {
            new NameTag("Madonna");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
