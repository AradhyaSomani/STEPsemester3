/** Traffic light that can only move forward: RED -> GREEN -> YELLOW -> RED. */
public class TrafficLight {

    private static final String[] CYCLE = {"RED", "GREEN", "YELLOW"};

    private final String id;
    private int position;

    public TrafficLight(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id;
        this.position = 0;
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return CYCLE[position];
    }

    public String next() {
        position = (position + 1) % CYCLE.length;
        return CYCLE[position];
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("getColor() -> " + t.getColor());
        for (int i = 0; i < 4; i++) {
            System.out.println("next() -> " + t.next());
        }
    }
}
