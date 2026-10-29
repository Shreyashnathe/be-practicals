# N-Queens Problem Using Backtracking

## Aim
To place N Queens on an N × N chessboard such that no two Queens attack each other, using backtracking. The first Queen is placed at a user-specified position.

## Algorithm
1. Place the first Queen at the given position.
2. Move to the next row and check each column for a safe position.
3. Place a Queen if no other Queen exists in the same column or diagonal.
4. If no safe position is available, backtrack and change the previous Queen's position.
5. Repeat until all N Queens are placed or no solution exists.

## Test Case

**Input**
```text
Enter N: 4
Enter the first Queen position: 1 2
```

**Expected Output**
```text
0 1 0 0
0 0 0 1
1 0 0 0
0 0 1 0
```

`1` represents a Queen; `0` represents an empty cell.

## Complexity Analysis
- **Time Complexity:** O(N!) (upper-bound estimate for row-by-row backtracking).
- **Space Complexity:** O(N²) for the board.

## Conclusion
Backtracking systematically explores valid Queen positions and reverses choices when they lead to a dead end, finding a valid N-Queens arrangement if one exists for the fixed first Queen.