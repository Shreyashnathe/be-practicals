import java.util.Scanner;

public class FibonacciRecursive {
    static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Calculate Fibonacci number\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter n (0 to 40 recommended): ");
                    int n = sc.nextInt();
                    if (n < 0 || n > 40) System.out.println("Enter n from 0 to 40.");
                    else System.out.println("Fibonacci number = " + fibonacci(n));
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