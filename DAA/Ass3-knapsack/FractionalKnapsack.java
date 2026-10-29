import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack {
    static class Item {
        int weight, value;
        double ratio;
        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            ratio = (double) value / weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Solve Fractional Knapsack\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter number of items: ");
                    int n = sc.nextInt();
                    if (n <= 0) { System.out.println("Invalid number."); break; }
                    Item[] items = new Item[n];
                    boolean valid = true;
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter weight and value: ");
                        int w = sc.nextInt(), v = sc.nextInt();
                        if (w <= 0 || v < 0) { valid = false; break; }
                        items[i] = new Item(w, v);
                    }
                    if (!valid) { System.out.println("Weight must be positive and value non-negative."); break; }
                    System.out.print("Enter knapsack capacity: ");
                    int capacity = sc.nextInt();
                    if (capacity < 0) { System.out.println("Invalid capacity."); break; }
                    Arrays.sort(items, Comparator.comparingDouble((Item x) -> x.ratio).reversed());
                    double total = 0;
                    for (Item item : items) {
                        if (capacity == 0) break;
                        if (item.weight <= capacity) {
                            capacity -= item.weight;
                            total += item.value;
                        } else {
                            total += item.value * ((double) capacity / item.weight);
                            capacity = 0;
                        }
                    }
                    System.out.printf("Maximum value = %.2f%n", total);
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
