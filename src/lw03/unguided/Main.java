package lw03.unguided;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        Map<String, Integer> enrollment = new LinkedHashMap<>();
        List<String> checkResults = new ArrayList<>();
        int rejectedOperations = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            String type = parts[0];

            if (type.equals("REGISTER")) {
                String courseCode = parts[1];
                int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    int currentCount = enrollment.getOrDefault(courseCode, 0);
                    enrollment.put(courseCode, currentCount + count);
                }
            } else if (type.equals("WITHDRAW")) {
                String courseCode = parts[1];
                int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    int currentCount = enrollment.getOrDefault(courseCode, 0);
                    if (enrollment.containsKey(courseCode) && currentCount >= count) {
                        enrollment.put(courseCode, currentCount - count);
                    } else {
                        rejectedOperations++;
                    }
                }
            } else if (type.equals("CHECK")) {
                String courseCode = parts[1];
                if (enrollment.containsKey(courseCode)) {
                    checkResults.add(courseCode + ": " + enrollment.get(courseCode) + " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }
            }
        }
        sc.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println("===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
        }
        System.out.println("Rejected operations: " + rejectedOperations);
    }
}