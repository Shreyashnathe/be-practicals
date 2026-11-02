import java.util.*;

public class FractionalKnapsack {

    static class Item {
        int weight, value;

        Item(int w, int v) {
            weight = w;
            value = v;
        }
    }

    // Calculate maximum value
    static void knapsack(Scanner sc) {
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value: ");
            int w = sc.nextInt();
            int v = sc.nextInt();

            if (w <= 0 || v < 0) {
                System.out.println("Invalid input.");
                return;
            }

            items[i] = new Item(w, v);
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        if (capacity < 0) {
            System.out.println("Invalid capacity.");
            return;
        }

        // Sort items by value/weight ratio
        Arrays.sort(items, (a, b) ->
            Double.compare((double)b.value / b.weight,
                           (double)a.value / a.weight));

        double total = 0;

        for (Item item : items) {
            if (capacity == 0) break;

            if (item.weight <= capacity) {
                total += item.value;
                capacity -= item.weight;
            } else {
                total += (double)item.value * capacity / item.weight;
                capacity = 0;
            }
        }

        System.out.printf("Maximum value = %.2f%n", total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Solve Fractional Knapsack");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    knapsack(sc);
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