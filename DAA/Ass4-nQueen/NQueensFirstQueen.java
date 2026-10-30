// Design an n-Queens matrix with the first queen already placed; use backtracking to place the remaining queens. 

import java.util.Scanner;

public class NQueensFirstQueen {
    static boolean isSafe(int[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) if (board[i][col] == 1) return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 1) return false;
        return true;
    }

    static boolean solve(int[][] board, int row, int n) {
        if (row == n) return true;
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;
                if (solve(board, row + 1, n)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Solve N-Queens with a fixed first queen\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter N: ");
                    int n = sc.nextInt();
                    if (n <= 0) { System.out.println("N must be positive."); break; }
                    int[][] board = new int[n][n];
                    System.out.print("Enter first queen row and column (1-based): ");
                    int row = sc.nextInt(), col = sc.nextInt();
                    if (row < 1 || row > n || col < 1 || col > n) {
                        System.out.println("Invalid position."); break;
                    }
                    board[row - 1][col - 1] = 1;
                    if (solve(board, row, n)) {
                        System.out.println("Solution (1 = queen, 0 = empty):");
                        for (int[] line : board) {
                            for (int cell : line) System.out.print(cell + " ");
                            System.out.println();
                        }
                    } else System.out.println("No solution for this first queen position.");
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
