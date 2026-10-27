import java.util.Arrays;

/** Auto-draft engine: overloaded eligibility rules plus ranking through Comparable and Arrays.sort. */
public class FantasyAutoDraftRanker {

    private static final int ESTABLISHED_MATCHES = 10;
    private static final int MIN_MATCHES_FOR_FIT_PLAYERS = 5;

    static class Player implements Comparable<Player> {
        private final String name;
        private final int matchesPlayed;
        private final double battingAverage;    // used as the fantasy-points value
        private final boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            if (matchesPlayed < 0) {
                throw new IllegalArgumentException("matchesPlayed cannot be negative for " + name);
            }
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        public String getName() { return name; }
        public int getMatchesPlayed() { return matchesPlayed; }
        public double getBattingAverage() { return battingAverage; }
        public boolean isInjured() { return injured; }

        static boolean isDraftable(int matchesPlayed) {
            return matchesPlayed >= ESTABLISHED_MATCHES;
        }

        static boolean isDraftable(int matchesPlayed, boolean injured) {
            return matchesPlayed >= MIN_MATCHES_FOR_FIT_PLAYERS && !injured;
        }

        @Override
        public int compareTo(Player other) {
            return Double.compare(other.battingAverage, this.battingAverage);    // descending
        }
    }

    public static String draftAndRank(Player[] players) {
        Player[] draftable = new Player[players.length];
        int count = 0;
        for (Player player : players) {
            if (Player.isDraftable(player.getMatchesPlayed())
                    || Player.isDraftable(player.getMatchesPlayed(), player.isInjured())) {
                draftable[count++] = player;
            }
        }
        Player[] ranked = Arrays.copyOf(draftable, count);
        Arrays.sort(ranked);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ranked.length; i++) {
            if (i > 0) result.append(" | ");
            result.append(i + 1).append(". ").append(ranked[i].getName());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        try {
            Player[] squad = {
                new Player("Virat", 15, 48.0, false), new Player("Rahul", 7, 55.0, false),
                new Player("Sameer", 3, 60.0, false), new Player("Dev", 12, 20.0, true)
            };
            System.out.println(draftAndRank(squad));
            new Player("Broken", -1, 10.0, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
