import java.util.*;

public class Huffman {

    static class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    // Build Huffman Tree
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

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.println(
                root.ch + " : " + (code.isEmpty() ? "0" : code)
            );
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    // Store node positions
    static Map<Node, Integer> positions = new IdentityHashMap<>();

    static List<List<Node>> levels = new ArrayList<>();

    static int leafCount;
    static int slotWidth;

    // Assign horizontal positions to nodes
    static int assignPositions(Node root) {

        if (root == null) {
            return 0;
        }

        // Leaf node
        if (root.left == null && root.right == null) {

            int position = leafCount * slotWidth;
            leafCount++;

            positions.put(root, position);

            return position;
        }

        int left = assignPositions(root.left);
        int right = assignPositions(root.right);

        int position = (left + right) / 2;

        positions.put(root, position);

        return position;
    }

    // Store nodes level by level
    static void storeLevels(Node root, int level) {

        if (root == null) {
            return;
        }

        while (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(root);

        storeLevels(root.left, level + 1);
        storeLevels(root.right, level + 1);
    }

    // Print properly aligned Huffman Tree
    static void printTree(Node root) {

        if (root == null) {
            return;
        }

        positions.clear();
        levels.clear();

        leafCount = 0;

        int maxLabelWidth = findMaxLabelWidth(root);
        slotWidth = Math.max(8, maxLabelWidth + 6);

        assignPositions(root);
        storeLevels(root, 0);

        int width = Math.max(
            1,
            leafCount * slotWidth + maxLabelWidth
        );

        for (int level = 0; level < levels.size(); level++) {

            char[] nodeLine = new char[width];
            char[] branchLine = new char[width];

            Arrays.fill(nodeLine, ' ');
            Arrays.fill(branchLine, ' ');

            // Print nodes
            for (Node node : levels.get(level)) {

                int x = positions.get(node);

                String label = String.valueOf(node.freq);

                int start = x - label.length() / 2;

                for (int i = 0; i < label.length(); i++) {

                    if (start + i >= 0 && start + i < width) {
                        nodeLine[start + i] = label.charAt(i);
                    }
                }

                // Left branch
                if (node.left != null) {

                    int childX = positions.get(node.left);
                    int branchX = (x + childX) / 2;

                    branchLine[branchX] = '/';
                }

                // Right branch
                if (node.right != null) {

                    int childX = positions.get(node.right);
                    int branchX = (x + childX) / 2;

                    branchLine[branchX] = '\\';
                }
            }

            System.out.println(new String(nodeLine).stripTrailing());

            if (level < levels.size() - 1) {
                System.out.println(new String(branchLine).stripTrailing());
            }
        }
    }

    // Find maximum frequency label width
    static int findMaxLabelWidth(Node root) {

        if (root == null) {
            return 1;
        }

        int current = String.valueOf(root.freq).length();

        int left = findMaxLabelWidth(root.left);
        int right = findMaxLabelWidth(root.right);

        return Math.max(current, Math.max(left, right));
    }

    // Generate Huffman Encoding
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

        System.out.println("\nHuffman Tree:");
        printTree(root);

        System.out.println("\nHuffman Codes:");
        printCodes(root, "");
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n1. Generate Huffman Codes");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    generateHuffman(sc);
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