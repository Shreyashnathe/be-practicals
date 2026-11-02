import java.util.*;

public class Knapsack01DP {

    // Dynamic Programming
    static int knapsack(int[] wt, int[] val, int cap) {
        int n = wt.length;
        int[][] dp = new int[n + 1][cap + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= cap; w++) {
                dp[i][w] = dp[i - 1][w];

                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w],
                        val[i - 1] + dp[i - 1][w - wt[i - 1]]);
                }
            }
        }
        return dp[n][cap];
    }

    // Take input
    static void solve(Scanner sc) {
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number.");
            return;
        }

        int[] wt = new int[n];
        int[] val = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value: ");
            wt[i] = sc.nextInt();
            val[i] = sc.nextInt();

            if (wt[i] <= 0 || val[i] < 0) {
                System.out.println("Invalid input.");
                return;
            }
        }

        System.out.print("Enter capacity: ");
        int cap = sc.nextInt();

        if (cap < 0) {
            System.out.println("Invalid capacity.");
            return;
        }

        System.out.println("Maximum value = " + knapsack(wt, val, cap));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. 0/1 Knapsack using DP");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    solve(sc);
                    break;
                case 0:
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}