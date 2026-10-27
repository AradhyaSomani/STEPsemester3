/** Quiz scorecard that hides the answers and exposes only the score. */
public class Scorecard {

    private final boolean[] results;
    private int recordedCount;

    public Scorecard(int totalQuestions) {
        if (totalQuestions <= 0) {
            throw new IllegalArgumentException("Question count must be positive.");
        }
        this.results = new boolean[totalQuestions];
    }

    public boolean recordAnswer(boolean correct) {
        if (recordedCount >= results.length) {
            System.out.println("Answer ignored: all " + results.length + " questions already recorded");
            return false;
        }
        results[recordedCount++] = correct;
        return true;
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < recordedCount; i++) {
            if (results[i]) score++;
        }
        return score;
    }

    public static void main(String[] args) {
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("getScore() -> " + sc.getScore());
        sc.recordAnswer(true);                    // fifth answer is rejected
        System.out.println("getScore() -> " + sc.getScore());
    }
}
