package interviews.Atlassian.OnlineCoding;

import java.util.*;

public class TeleporterSolution {
    public static void main(String[] argv) {
        String[] teleporters1 = {"3,1", "4,2", "5,10"};
        String[] teleporters2 = {"5,10", "6,22", "39,40", "40,49", "47,29"};
        String[] teleporters3 = {"6,18", "36,26", "41,21", "49,55", "54,52",
                "71,58", "74,77", "78,76", "80,73", "92,85"};
        String[] teleporters4 = {"97,93", "99,81", "36,33", "92,59", "17,3",
                "82,75", "4,1", "84,79", "54,4", "88,53",
                "91,37", "60,57", "61,7", "62,51", "31,19"};
        String[] teleporters5 = {"3,8", "8,9", "9,3"};

        System.out.println(destinations(teleporters1, 6, 0, 20)); // [1, 2, 10, 6]
        System.out.println(destinations(teleporters2, 6, 46, 100)); // [48, 49, 50, 51, 52, 29]
        System.out.println(destinations(teleporters2, 10, 0, 50)); // [1, 2, 3, 4, 7, 8, 9, 10, 22]
        System.out.println(destinations(teleporters3, 10, 95, 100)); // [96, 97, 98, 99, 100]
        System.out.println(destinations(teleporters3, 10, 70, 100)); // [72, 73, 75, 76, 77, 79, 58]
        System.out.println(destinations(teleporters4, 6, 0, 100)); // [1, 2, 3, 5, 6]
        System.out.println(destinations(teleporters5, 7, 2, 20)); // [3, 4, 5, 6, 7, 8, 9]
    }

    public static List<Integer> destinations(String[] teleporters, int dieSides, int start, int lastSquare) {
        // Create a map to hold the teleporters
        Map<Integer, Integer> teleporterMap = new HashMap<>();
        for (String teleporter : teleporters) {
            String[] parts = teleporter.split(",");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            teleporterMap.put(from, to);
        }

        // Set to store unique destinations
        Set<Integer> resultSet = new HashSet<>();

        // Calculate all possible destinations
        for (int roll = 1; roll <= dieSides; roll++) {
            int newPosition = start + roll;
            if (newPosition > lastSquare) {
                newPosition = lastSquare;
            }
            // Follow teleporter if there is one
            if (teleporterMap.containsKey(newPosition)) {
                newPosition = teleporterMap.get(newPosition);
            }
            resultSet.add(newPosition);
        }

        // Convert result set to list
        return new ArrayList<>(resultSet);
    }
}