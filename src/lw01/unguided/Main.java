package lw01.unguided;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("rentals.txt"));

        int count = sc.nextInt();
        Rental[] rentals = new Rental[count];

        for (int i = 0; i < count; i++) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            if (type.equalsIgnoreCase("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days, units);
            } else {
                rentals[i] = new ProjectorRental(id, days, units);
            }
        }
        sc.close();

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }

    }
}
