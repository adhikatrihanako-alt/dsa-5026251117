package lw04.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }
 
        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        
        int passes = 3;
        for (int i = 1; i <= passes && i < n; i++) {
            int key = branches[i];
            int j = i - 1;
            
            
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }

        
        for (int i = 0; i < n; i++) {
            System.out.print(branches[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        
        for (int t = 0; t < m; t++) {
            int target = targets[t];
            int foundIndex = -1;

            for (int i = 0; i < n; i++) {
                if (branches[i] == target) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}
    