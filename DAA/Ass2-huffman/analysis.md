# Huffman Encoding Using Greedy Strategy

## 1. Aim
To implement Huffman Encoding using a greedy algorithm to generate variable-length, prefix-free binary codes based on character frequencies.

## 2. Greedy Strategy

Huffman Encoding uses a **greedy approach** by repeatedly selecting the two nodes with the lowest frequencies and combining them into a new node.

### Steps of the Algorithm

1. Read the characters and their frequencies.
2. Insert each character into a min-heap based on its frequency.
3. Remove the two nodes with the smallest frequencies.
4. Create a new parent node with a frequency equal to the sum of the two selected nodes.
5. Make the two selected nodes the left and right children of the new parent.
6. Insert the parent node back into the min-heap.
7. Repeat steps 3–6 until only one node remains.
8. The remaining node becomes the root of the Huffman tree.
9. Traverse the tree, assigning `0` to left edges and `1` to right edges, to generate the Huffman codes.

## 3. Test Cases

### Test Case 1: Normal Input

**Input**
```text
5
a 5
b 9
c 12
d 13
e 16
```

**Expected Output**
```text
Huffman Codes:
e : 00
c : 01
a : 100
b : 101
d : 11
```

*Note: Huffman codes may differ depending on how ties are resolved, but the resulting codes should remain valid and optimal.*

### Test Case 2: Single Character

**Input**
```text
1
a 10
```

**Expected Output**
```text
Huffman Codes:
a : 0
```

### Test Case 3: Equal Frequencies

**Input**
```text
4
a 5
b 5
c 5
d 5
```

**Expected Output**

A valid result will assign two-bit codes to all four characters, such as:

```text
a : 00
b : 01
c : 10
d : 11
```

*Note: The exact codes can vary because the frequencies are equal.*

### Test Case 4: Invalid Number of Characters

**Input**
```text
0
```

**Expected Output**
```text
Invalid input!
```

### Test Case 5: Invalid Frequency

**Input**
```text
2
a 5
b 0
```

**Expected Output**
```text
Invalid frequency!
```

## 4. Complexity Analysis

Let `n` be the number of distinct characters.

| Operation | Time Complexity |
|---|---|
| Insert all nodes into the min-heap | O(n log n) |
| Construct the Huffman tree | O(n log n) |
| Traverse the tree to generate codes | O(n) excluding output-string copying |
| **Overall time complexity** | **O(n log n)** |
| **Space complexity** | **O(n)** |

### Explanation
- **Time Complexity:** The algorithm performs `n - 1` merging operations. Each operation removes two nodes and inserts one new node into the min-heap, taking O(log n) time.
- **Space Complexity:** The min-heap and Huffman tree require O(n) space. The recursive traversal uses additional stack space proportional to the tree height.

## 5. Applications

- Data compression
- File compression formats
- Lossless encoding
- Efficient storage and transmission of data

## 6. Conclusion

Huffman Encoding demonstrates the greedy algorithm by repeatedly combining the two least frequent nodes. It constructs an optimal prefix-free binary code for the given character frequencies, minimizing the weighted average code length.