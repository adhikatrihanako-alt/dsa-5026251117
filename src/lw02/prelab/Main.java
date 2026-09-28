package lw02.prelab;

import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>(transactionsList);
        Stack<String[]> failed = new Stack<>();

        while (sc.hasNext()) {
            String name = sc.next();
            String act = sc.next();
            int amount = sc.nextInt();

            String[] transaction = { name, act, String.valueOf(amount) };
            transactionsList.add(transaction);

            boolean found = false;
            for (String[] c : customerList) {
                if (c[0].equals(name)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                customerList.add(new String[] { name, "0" });
        }
        sc.close();

        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();
            String name = transaction[0];
            String act = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;

            for (String[] c : customerList) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }

            }
            int balance = Integer.parseInt(customer[1]);

            if (act.equals("DEPOSIT")) {
                customer[1] = String.valueOf(balance + amount);
            } else if (amount > balance) {
                failed.push(transaction);
            } else {
                customer[1] = String.valueOf(balance - amount);
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customerList)
            System.out.println(c[0] + " : " + c[1]);

        System.out.println("\n=== Failed Transactions ===");

        while (!failed.isEmpty()) {
            String[] transaction = failed.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        }
    }

}
