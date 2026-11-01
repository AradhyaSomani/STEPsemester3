import java.util.HashMap;
import java.util.Map;

/**
 * buildLookup turns the unsorted list into a HashMap once (O(n) time, O(n) extra space).
 * getDepartment is then O(1) on average. With dozens of queries a day and few updates a month,
 * paying O(n) once is far cheaper than scanning the list (O(n)) for every query.
 */
public class EmployeeDirectoryLookup {

    private static final String UNKNOWN = "Unknown";

    public static Map<String, String> buildLookup(String[][] records) {
        if (records == null) {
            throw new IllegalArgumentException("Records cannot be null.");
        }
        Map<String, String> lookup = new HashMap<>();
        for (String[] record : records) {
            if (record == null || record.length != 2) {
                throw new IllegalArgumentException("Each record needs exactly an ID and a department.");
            }
            lookup.put(record[0], record[1]);
        }
        return lookup;
    }

    public static String getDepartment(Map<String, String> lookup, String employeeId) {
        return lookup.getOrDefault(employeeId, UNKNOWN);
    }

    public static void main(String[] args) {
        String[][] records = {
            {"E245", "Finance"}, {"E110", "Engineering"}, {"E390", "Marketing"}, {"E101", "Engineering"}
        };
        Map<String, String> lookup = buildLookup(records);
        System.out.println("\"" + getDepartment(lookup, "E390") + "\"");
        System.out.println("\"" + getDepartment(lookup, "E999") + "\"");
    }
}
