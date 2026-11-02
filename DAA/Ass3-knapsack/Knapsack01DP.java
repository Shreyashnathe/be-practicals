import java.util.*;

public class Knapsack01DP {

    // 0/1 Knapsack using Dynamic Programming
    static int knapsack(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w];
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w],
                        value[i - 1] + dp[i - 1][w - weight[i - 1]]);
                }
            }
        }
        return dp[n][capacity];
    }

    // Take input and solve the problem
    static void solve(Scanner sc) {
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Invalid number.");
            return;
        }
        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value: ");
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
            if (weight[i] <= 0 || value[i] < 0) {
                System.out.println("Invalid input.");
                return;
            }
        }
        System.out.print("Enter capacity: ");
        int capacity = sc.nextInt();
        if (capacity < 0) {
            System.out.println("Invalid capacity.");
            return;
        }
        System.out.println("Maximum value = " +
            knapsack(weight, value, capacity));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Solve 0/1 Knapsack using DP");
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
    }
}