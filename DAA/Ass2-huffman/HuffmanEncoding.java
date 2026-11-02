import java.util.*;

public class HuffmanEncoding {

    static class Node {
        char ch;
        int freq;
        Node left, right;
        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    // Generate Huffman Tree
    static Node buildTree(char[] chars, int[] freq, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.freq, b.freq)
        );
        for (int i = 0; i < n; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;

            pq.add(parent);
        }

        return pq.poll();
    }

    // Print Huffman Codes
    static void printCodes(Node root, String code) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + (code.isEmpty() ? "0" : code));
            return;
        }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    // Take input and generate codes
    static void generateHuffman(Scanner sc) {
        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Invalid number.");
            return;
        }
        char[] chars = new char[n];
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter character and frequency: ");
            chars[i] = sc.next().charAt(0);
            freq[i] = sc.nextInt();
            if (freq[i] <= 0) {
                System.out.println("Frequency must be positive.");
                return;
            }
        }
        Node root = buildTree(chars, freq, n);
        System.out.println("Huffman Codes:");
        printCodes(root, "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Generate Huffman Codes");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            if (choice == 1) {
                generateHuffman(sc);
            } else if (choice == 0) {
                System.out.println("Program ended.");
            } else {
                System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}