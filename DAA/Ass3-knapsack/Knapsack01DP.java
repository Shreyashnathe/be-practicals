import java.util.Scanner;

public class Knapsack01DP {
    static int knapsack(int capacity, int[] weight, int[] value) {
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w];
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], value[i - 1] + dp[i - 1][w - weight[i - 1]]);
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Solve 0/1 Knapsack using DP\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter number of items: ");
                    int n = sc.nextInt();
                    if (n <= 0) { System.out.println("Invalid number."); break; }
                    int[] weight = new int[n], value = new int[n];
                    boolean valid = true;
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter weight and value for item " + (i + 1) + ": ");
                        weight[i] = sc.nextInt();
                        value[i] = sc.nextInt();
                        if (weight[i] <= 0 || value[i] < 0) { valid = false; break; }
                    }
                    if (!valid) { System.out.println("Weights must be positive and values non-negative."); break; }
                    System.out.print("Enter capacity: ");
                    int capacity = sc.nextInt();
                    if (capacity < 0) { System.out.println("Invalid capacity."); break; }
                    System.out.println("Maximum value = " + knapsack(capacity, weight, value));
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
