/** Reports the first duplicated team name using nested loops only. */
public class DuplicateTeamNameFinder {

    public static String findDuplicateTeam(String[] teamNames) {
        if (teamNames == null) {
            throw new IllegalArgumentException("Team names cannot be null.");
        }
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i] != null && teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        String[][] samples = {
            {"ByteForce", "CodeCrafters", "ByteForce"},
            {"ByteForce", "CodeCrafters", "NullPointers"}
        };
        for (String[] teams : samples) {
            System.out.println(findDuplicateTeam(teams));
        }
    }
}
