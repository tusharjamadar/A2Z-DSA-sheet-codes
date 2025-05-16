package SubsequencesPattern;
/*
Problem: 79. Word Search

Given an m x n grid of characters `board` and a string `word`, return true if `word` exists in the grid.

The word must be constructed from letters of sequentially adjacent cells (up/down/left/right).
The same letter cell may not be used more than once.

-----------------------------------------------------------------------

Intuition:
We need to search for the word starting from every cell in the grid.
We use backtracking to explore all four directions recursively and mark visited cells to prevent reuse.

-----------------------------------------------------------------------

Algorithm:
1. Iterate through each cell in the board.
2. If the current cell matches the first character of the word, call the backtracking function.
3. In backtracking:
   a. Check for base case (entire word matched).
   b. If out of bounds or character does not match, return false.
   c. Mark the current cell as visited.
   d. Recur for all four directions (up/down/left/right).
   e. If a path returns true, the word is found.
   f. Unmark the cell (backtrack).
4. Return true if any path matches, otherwise false.

-----------------------------------------------------------------------

Time Complexity:
- O(M * N * 4^L), where:
  M = rows, N = columns of the board, L = length of the word
- 4^L is the number of paths we can try (each letter can branch in 4 directions)

Space Complexity:
- O(L) recursion depth
- O(M * N) for visited matrix

*/

class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;          // Number of rows
        int n = board[0].length;       // Number of columns

        boolean[][] visited = new boolean[m][n];  // To keep track of visited cells
        boolean result = false;       // Flag to store final result

        // Try starting from every cell
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // If the character matches the first character of the word
                if(board[i][j] == word.charAt(0)){
                    // Start backtracking from here
                    result = backtrack(board, word, visited, i, j, 0);
                    if(result) return true; // If word is found, return true
                }
            }
        }

        return false; // If no path matched
    }

    // Backtracking function to search for word starting from index
    private boolean backtrack(char[][] board, String word, boolean[][] visited, int i, int j, int index){
        // If full word is matched
        if(index == word.length()) return true;

        // If out of bounds or already visited or character mismatch
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length 
           || visited[i][j] || board[i][j] != word.charAt(index)){
            return false;
        }

        // Mark the cell as visited
        visited[i][j] = true;

        // Explore all 4 directions
        if(backtrack(board, word, visited, i + 1, j, index + 1) || // down
           backtrack(board, word, visited, i - 1, j, index + 1) || // up
           backtrack(board, word, visited, i, j + 1, index + 1) || // right
           backtrack(board, word, visited, i, j - 1, index + 1))   // left
        {
            return true; // If any path returns true, propagate it
        }

        // Backtrack: unmark visited cell
        visited[i][j] = false;

        return false; // No valid path from this cell
    }

    // Optional: add main to test
    public static void main(String[] args) {
        WordSearch sol = new WordSearch();
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        System.out.println(sol.exist(board, "ABCCED")); // true
        System.out.println(sol.exist(board, "SEE"));    // true
        System.out.println(sol.exist(board, "ABCB"));   // false
    }
}
