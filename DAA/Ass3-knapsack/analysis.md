1. What is the 0/1 Knapsack Problem?
The 0/1 Knapsack Problem is an optimization problem in which we have:
- A bag (knapsack) with a fixed capacity.
- Multiple items, each having a weight and a value.
- We need to select items to get the maximum total value without exceeding the bag's capacity.
Why is it called 0/1?
- 1 means we select the complete item.
- 0 means we do not select the item.
We cannot take a fraction of an item.
Example
Suppose we have:
Item	Weight	Value
1	10	60
2	20	100
3	30	120

Knapsack capacity = 50.

We select:
- Item 2: Weight 20, Value 100
- Item 3: Weight 30, Value 120
Total weight = 50.

Maximum value = 220.

2. Logic behind Dynamic Programming
For every item, we have two choices:
1. Include the item.
2. Exclude the item.
We calculate both possibilities and select the one that gives the maximum value.

We use a 2D array called dp to store the results of smaller problems.
- Rows represent the number of items considered.
- Columns represent the knapsack capacity.
- dp[i][w] represents the maximum value using the first i items with capacity w.
The main formula is:
\[
dp[i][w] = \max(\text{Include},\text{Exclude})
\]


3. Code Explanation
1. knapsack() method
static int knapsack(int[] wt, int[] val, int cap) {
    int n = wt.length;
    int[][] dp = new int[n + 1][cap + 1];


- wt[] stores item weights.
- val[] stores item values.
- cap is the maximum capacity.
- dp[][] stores the maximum value for different items and capacities.
2. Iterate through items and capacities
for (int i = 1; i <= n; i++) {
    for (int w = 0; w <= cap; w++) {


We check every item and every possible capacity.

3. Initially exclude the item
dp[i][w] = dp[i - 1][w];


This means we do not select the current item. We use the previous maximum value.

4. Check whether we can include the item
if (wt[i - 1] <= w) {
    dp[i][w] = Math.max(dp[i][w],
        val[i - 1] + dp[i - 1][w - wt[i - 1]]);
}


If the item's weight is less than or equal to the current capacity, we have two choices:
- Exclude: dp[i - 1][w]
- Include: val[i - 1] + dp[i - 1][w - wt[i - 1]]
Math.max() selects the option with the greater value.

5. Return the answer
return dp[n][cap];


Returns the maximum value using all items and the given capacity.

6. solve() method

This method:
- Takes the number of items.
- Accepts the weight and value of each item.
- Takes the knapsack capacity.
- Calls knapsack() and displays the answer.
7. main() method

Displays a menu using do-while and switch:
- Choice 1: Solve the Knapsack problem.
- Choice 0: Exit the program.

4. Complexity Analysis
Complexity	Value
Time Complexity	\(O(n * W)\)
Space Complexity	\(O(n * W)\)

Here, n = number of items and W = knapsack capacity.

5. Important Viva Questions
Q1. Why is it called 0/1 Knapsack?

Because each item can either be selected completely (1) or not selected (0).

Q2. Why do we use Dynamic Programming?

Because the problem has overlapping subproblems and optimal substructure. We store previously calculated results to avoid repeated calculations.

Q3. What is the role of Math.max()?

It compares the values obtained by including and excluding an item and selects the maximum.

Q4. Can we select half of an item?

No. Fractional selection is allowed in Fractional Knapsack, not 0/1 Knapsack.

Q5. What is the difference between 0/1 and Fractional Knapsack?
- 0/1 Knapsack: Select the complete item or reject it.
- Fractional Knapsack: We can select a fraction of an item.
Q6. What is the time complexity?

\(O(n \times W)\), where n is the number of items and W is the capacity.

Remember this for viva
The main logic of this program is:

For every item, check whether including it or excluding it gives the maximum value. Store that result in the DP table and finally return dp[n][cap].