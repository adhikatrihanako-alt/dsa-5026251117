package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File("jobs.txt"))) {
            while (fileScanner.hasNext()) {
                String type = fileScanner.next();
                String id = fileScanner.next();
                int pages = fileScanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("jobs.txt not found");
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}