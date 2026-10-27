import java.util.Arrays;

/** Shortlists candidates with overloaded eligibility rules and ranks them using Arrays.sort. */
public class PlacementShortlistRanker {

    private static final double CGPA_ONLY_CUTOFF = 7.0;
    private static final double BORDERLINE_CGPA_CUTOFF = 6.5;
    private static final int MIN_CODING_SCORE = 60;

    static class Candidate implements Comparable<Candidate> {
        private final String name;
        private final double cgpa;
        private final int codingScore;

        public Candidate(String name, double cgpa, int codingScore) {
            if (cgpa < 0 || cgpa > 10 || codingScore < 0 || codingScore > 100) {
                throw new IllegalArgumentException("Invalid CGPA or coding score for " + name);
            }
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        public String getName() { return name; }
        public double getCgpa() { return cgpa; }
        public int getCodingScore() { return codingScore; }

        public double getCompositeScore() {
            return cgpa * 10 + codingScore * 0.5;
        }

        static boolean isEligible(double cgpa) {
            return cgpa >= CGPA_ONLY_CUTOFF;
        }

        static boolean isEligible(double cgpa, int codingScore) {
            return cgpa >= BORDERLINE_CGPA_CUTOFF && codingScore >= MIN_CODING_SCORE;
        }

        @Override
        public int compareTo(Candidate other) {
            return Double.compare(other.getCompositeScore(), this.getCompositeScore());   // descending
        }
    }

    public static String shortlistAndRank(Candidate[] candidates) {
        Candidate[] shortlisted = new Candidate[candidates.length];
        int count = 0;
        for (Candidate candidate : candidates) {
            if (Candidate.isEligible(candidate.getCgpa())
                    || Candidate.isEligible(candidate.getCgpa(), candidate.getCodingScore())) {
                shortlisted[count++] = candidate;
            }
        }
        Candidate[] ranked = Arrays.copyOf(shortlisted, count);
        Arrays.sort(ranked);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ranked.length; i++) {
            if (i > 0) result.append(" | ");
            result.append(i + 1).append(". ").append(ranked[i].getName())
                  .append(" (").append(ranked[i].getCompositeScore()).append(")");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        try {
            Candidate[] batch = {
                new Candidate("Aisha", 8.2, 40), new Candidate("Rohit", 6.8, 65),
                new Candidate("Meena", 6.0, 90), new Candidate("Karan", 7.5, 20)
            };
            System.out.println(shortlistAndRank(batch));
            new Candidate("Bad", 11.0, 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
