import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanEncoding {
    static class Node {
        char ch;
        int freq;
        Node left, right;
        Node(char ch, int freq) { this.ch = ch; this.freq = freq; }
    }

    static void printCodes(Node root, String code) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + (code.isEmpty() ? "0" : code));
            return;
        }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Generate Huffman codes\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter number of characters: ");
                    int n = sc.nextInt();
                    if (n <= 0) { System.out.println("Invalid number."); break; }
                    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);
                    boolean valid = true;
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter character and frequency: ");
                        char ch = sc.next().charAt(0);
                        int freq = sc.nextInt();
                        if (freq <= 0) { valid = false; break; }
                        pq.add(new Node(ch, freq));
                    }
                    if (!valid) { System.out.println("Frequency must be positive."); break; }
                    while (pq.size() > 1) {
                        Node left = pq.poll();
                        Node right = pq.poll();
                        Node parent = new Node('\0', left.freq + right.freq);
                        parent.left = left;
                        parent.right = right;
                        pq.add(parent);
                    }
                    System.out.println("Huffman codes:");
                    printCodes(pq.poll(), "");
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