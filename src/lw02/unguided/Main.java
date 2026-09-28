package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        LinkedList<String[]> requestList = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();
        LinkedList<String[]> memberList = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        while (sc.hasNext()) {
            String name = sc.next();
            String title = sc.next();

            String[] request = { name, title };
            requestList.add(request);

            boolean found = false;
            for (String member[] : memberList) {
                if (member[0].equals(name)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                memberList.add(new String[] { name, "0" });
            }

            bookList.add(new String[] { "Kalkulus", "2" });
            bookList.add(new String[] { "Fisika", "1" });
            bookList.add(new String[] { "Statistika", "2" });

            while (!queue.isEmpty()) {
                String[] req = queue.poll();
                String name = req[0];
                String title = req[1];

                String[] book = null;
                for (String[] b : bookList) {
                    if (b[0].equalsIgnoreCase(title)) {
                        book = b;
                        break;
                    }
                }
            }

            int stock = (book != null) ? Integer.parseInt(book[1]) : 0;
            int borrowed = Integer.parseInt(memberList[1]);

            if (stock > 0 && borrowed < 2) {
                book[1] = String.valueOf(stock - 1);
                memberList[1] = String.valueOf(borrowed + 1);
                successList.add(req);
            } else {
                failedStack.push(req);
            }
        }

        System.out.println("=== SUCCESSFUL REQUESTS ===");
        for (String[] s : successList) {
            System.out.println(s[0] + " - " + s[1]);
        }

        System.out.println("\n=== REMAINING BOOK STOCK ===");
        for (String[] b : bookList) {
            System.out.println(b[0] + ": " + b[1]);
        }

        System.out.println("\n=== FAILED REQUESTS (LIFO Order) ===");
        while (!failedStack.isEmpty()) {
            String[] f = failedStack.pop();
            System.out.println(f[0] + " - " + f[1]);
        }
    }
}
