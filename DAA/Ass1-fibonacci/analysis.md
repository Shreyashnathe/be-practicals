## Complexity Comparison

| Approach  | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| Iterative | O(n)            | O(1)             |
| Recursive | O(2^n)          | O(n)             |

Fibonacci Series – Iterative and Recursive Approaches
1. What is the Fibonacci Series?
The Fibonacci series is a sequence in which each number is the sum of the previous two numbers.

The series starts with:
\[
0,\ 1,\ 1,\ 2,\ 3,\ 5,\ 8,\ 13,\ 21,\ldots
\]

Formula:
\[
F(n)=F(n-1)+F(n-2)
\]

Base conditions:
- \(F(0)=0\)
- \(F(1)=1\)
Your programs calculate the Fibonacci number at a given position using two approaches.
2. Iterative Approach
Logic: We use a loop instead of recursion. We maintain two variables representing the previous two Fibonacci numbers and calculate the next number.
Important code explanation
if (n <= 1) return n;


If n is 0 or 1, return it directly because these are the base values.
long a = 0, b = 1;


Initialize the first two Fibonacci numbers.
for (int i = 2; i <= n; i++) {
    long c = a + b;
    a = b;
    b = c;
}


- c = a + b: Calculate the next Fibonacci number.
- a = b: Move the second number to the first position.
- b = c: Store the newly calculated number.
return b;


Returns the required Fibonacci number.

Example: For n = 6:
i	a	b	c = a + b
Initial	0	1	-
2	1	1	1
3	1	2	2
4	2	3	3
5	3	5	5
6	5	8	8

Output: Fibonacci number = 8
Complexity
- Time: \(O(n)\) – The loop executes approximately n times.
- Space: \(O(1)\) – Only three variables are used, regardless of n.

3. Recursive Approach
Logic: We calculate the Fibonacci number by calling the same method recursively for the previous two numbers.
Important code explanation
static long fibonacci(int n) {
    if (n <= 1) return n;


This is the base condition. It stops recursion when n becomes 0 or 1.
return fibonacci(n - 1) + fibonacci(n - 2);


This is the recursive formula.

It calls the same method twice:
- fibonacci(n - 1) calculates the previous Fibonacci number.
- fibonacci(n - 2) calculates the number before that.
Finally, both results are added.
Example: n = 5
fibonacci(5)
    |
    +-- fibonacci(4)
    |       |
    |       +-- fibonacci(3)
    |       +-- fibonacci(2)
    |
    +-- fibonacci(3)


The calculation is:
\[
F(5)=F(4)+F(3)=3+2=5
\]

Output: Fibonacci number = 5
Complexity
- Time: \(O(2^n)\) – Each call creates two more recursive calls in most cases. More precisely, the running time grows exponentially.
- Space: \(O(n)\) – Recursive calls are stored in the call stack, with a maximum recursion depth of n.

4. Difference Between Iterative and Recursive
Feature	Iterative	Recursive
Technique	Loop	Function calls
Calculation	Uses previous two variables	Calls itself twice
Time complexity	\(O(n)\)	\(O(2^n)\)
Space complexity	\(O(1)\)	\(O(n)\)
Performance	Faster	Slower for large n
Stack usage	No recursion stack	Uses recursion stack

5. Why are input limits different?
Your programs use different input limits:
- Iterative: 0 to 90
- Recursive: 0 to 40
Reason:
1. Iterative approach is efficient, so it can calculate larger Fibonacci positions quickly.
2. Recursive approach performs repeated calculations, so it becomes very slow for larger values.
3. Java's long data type also has a maximum value, which is why the iterative program limits input to 90.
6. Important Viva Questions
Q1. What is Fibonacci series?

A series where each number is the sum of the previous two numbers.

Q2. What is recursion?

When a method calls itself to solve a smaller version of the same problem.

Q3. What is iteration?

Repeating a set of statements using loops such as for or while.

Q4. What is the base condition in recursion?

if (n <= 1) return n; It stops recursive calls.

Q5. Which approach is faster?

The iterative approach because it takes \(O(n)\) time, whereas the basic recursive approach takes exponential time.

Q6. Can we optimize recursive Fibonacci?

Yes. We can use Dynamic Programming or Memoization to avoid repeated calculations, reducing time complexity to \(O(n)\).

Quick revision for viva
- Iterative: Uses a loop and two variables to calculate Fibonacci numbers. Time: \(O(n)\), Space: \(O(1)\).
- Recursive: Calls the same function for n-1 and n-2. Time: \(O(2^n)\), Space: \(O(n)\).
Main difference: Iterative calculates each Fibonacci number once, whereas basic recursion recalculates the same Fibonacci values multiple times.