# Knapsack Problems in C++

## 1. 0/1 Knapsack Using Dynamic Programming

### Algorithm
1. Read the number of items, their weights, values, and knapsack capacity.
2. Create a DP table `dp[n+1][W+1]`, initialized with zeros.
3. For each item, check whether it can fit in the current capacity.
4. Choose the maximum value between including and excluding the item.
5. Return `dp[n][W]` as the maximum achievable value.

### Test Case

**Input**
```text
Enter number of items: 3
Enter weights of items:
10 20 30
Enter values of items:
60 100 120
Enter knapsack capacity: 50
```

**Expected Output**
```text
Maximum value: 220
```

**Explanation:** Select the items with weights 20 and 30, giving a total weight of 50 and a maximum value of 220.

### Complexity Analysis

Let `n` be the number of items and `W` be the knapsack capacity.

| Type | Complexity |
|---|---|
| Time | O(n × W) |
| Space | O(n × W) |

---

## 2. Fractional Knapsack Using Greedy Strategy

### Algorithm
1. Read the number of items, their weights, values, and knapsack capacity.
2. Calculate the value-to-weight ratio for each item.
3. Sort the items in descending order of their ratios.
4. Select complete items while they fit into the knapsack.
5. If the next item does not fit completely, select the fraction that fits.
6. Return the total value obtained.

### Test Case

**Input**
```text
Enter number of items: 3
Enter weight and value of each item:
10 60
20 100
30 120
Enter knapsack capacity: 50
```

**Expected Output**
```text
Maximum value: 240
```

**Explanation:** Select the first two items completely, obtaining a value of 160. The remaining capacity is 20, so take 20/30 of the third item, obtaining an additional value of 80. The maximum value is 240.

### Complexity Analysis

Let `n` be the number of items.

| Type | Complexity |
|---|---|
| Sorting | O(n log n) |
| Selecting items | O(n) |
| **Overall time** | **O(n log n)** |
| **Space** | **O(n)** |

---

## 3. Difference Between 0/1 and Fractional Knapsack

| Feature | 0/1 Knapsack | Fractional Knapsack |
|---|---|---|
| Item selection | Entire item or nothing | Items can be divided |
| Technique used | Dynamic Programming | Greedy Strategy |
| Optimal by value-to-weight ratio alone? | No | Yes |
| Time complexity | O(n × W) | O(n log n) |
| Space complexity | O(n × W) | O(n) |

## Conclusion

The 0/1 Knapsack problem is solved using Dynamic Programming to consider both including and excluding each item. Fractional Knapsack uses a Greedy Strategy by selecting items with the highest value-to-weight ratio first. Both approaches aim to maximize the total value within the given capacity.