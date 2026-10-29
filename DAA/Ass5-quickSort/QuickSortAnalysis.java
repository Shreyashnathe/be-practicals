import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSortAnalysis {
    static Random random = new Random();

    static int partition(int[] a, int low, int high) {
        int pivot = a[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i]; a[i] = a[j]; a[j] = temp;
            }
        }
        int temp = a[i + 1]; a[i + 1] = a[high]; a[high] = temp;
        return i + 1;
    }

    static void deterministicQuickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            deterministicQuickSort(a, low, p - 1);
            deterministicQuickSort(a, p + 1, high);
        }
    }

    static void randomizedQuickSort(int[] a, int low, int high) {
        if (low < high) {
            int randomIndex = low + random.nextInt(high - low + 1);
            int temp = a[randomIndex]; a[randomIndex] = a[high]; a[high] = temp;
            int p = partition(a, low, high);
            randomizedQuickSort(a, low, p - 1);
            randomizedQuickSort(a, p + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Compare deterministic and randomized Quick Sort\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter number of elements: ");
                    int n = sc.nextInt();
                    if (n <= 0) { System.out.println("Invalid number."); break; }
                    int[] original = new int[n];
                    System.out.println("Enter elements:");
                    for (int i = 0; i < n; i++) original[i] = sc.nextInt();
                    int[] a = Arrays.copyOf(original, n);
                    int[] b = Arrays.copyOf(original, n);
                    long start = System.nanoTime();
                    deterministicQuickSort(a, 0, n - 1);
                    long deterministicTime = System.nanoTime() - start;
                    start = System.nanoTime();
                    randomizedQuickSort(b, 0, n - 1);
                    long randomizedTime = System.nanoTime() - start;
                    System.out.println("Deterministic sorted: " + Arrays.toString(a));
                    System.out.println("Randomized sorted:   " + Arrays.toString(b));
                    System.out.println("Deterministic time (ns): " + deterministicTime);
                    System.out.println("Randomized time (ns):   " + randomizedTime);
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
