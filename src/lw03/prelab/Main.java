package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (sc1.hasNextLine()) {
            String line = sc1.nextLine().trim();
            if (line.isEmpty())
                continue;

            String[] parts = line.split(" ", 2);
            String command = parts[0];

            if (command.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);
            } else if (command.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(insertParts[0]);
                String song = insertParts[1];
                playlist.add(index, song);
            } else if (command.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song);
            }
        }
        sc1.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        while (sc2.hasNextLine()) {
            String name = sc2.nextLine().trim();
            if (name.isEmpty())
                continue;

            if (!participants.add(name)) {
                duplicateCount++;
            }

        }
        sc2.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int rank = 1;
        for (String participant : participants) {
            System.out.println(rank + ". " + participant);
            rank++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedcount = 0;

        while (sc3.hasNextLine()) {
            String line = sc3.nextLine().trim();
            if (line.isEmpty())
                continue;

            String[] parts = line.split(" ");
            String type = parts[0];
            String item = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
            } else if (type.equals("SELL")) {
                int currentQuantity = inventory.getOrDefault(item, 0);
                if (inventory.containsKey(item) && currentQuantity >= quantity) {
                    inventory.put(item, currentQuantity - quantity);
                } else {
                    failedcount++;
                }
            }
        }
        sc3.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed Sales: " + failedcount);
    }
}
