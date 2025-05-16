package Hard;
/*
Problem: 51. N-Queens

The N-Queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Return all distinct solutions to the N-Queens puzzle.
Each solution is represented as a list of strings.

Example:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

-----------------------------------------------------------------------

Intuition:
Use backtracking to try placing queens one column at a time, checking at each step whether it's safe to place a queen.
Prune invalid states early to optimize.

-----------------------------------------------------------------------

Time Complexity:
- Naive Version: O(N!) — backtracking with O(N^2) checks
- Optimized Version: O(N!) — but with O(1) checks using hash arrays

Space Complexity:
- O(N^2) for the board, and O(N) recursion depth

-----------------------------------------------------------------------
*/

import java.util.*;

public class NQueens {

    /*
    ================================
    Naive Backtracking Version
    ================================
    */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        // Initialize the board with '.'
        for (int i = 0; i < n; i++)
            Arrays.fill(board[i], '.');

        List<List<String>> res = new ArrayList<>();
        dfs(0, board, res);
        return res;
    }

    // Basic DFS to try placing queens column by column
    private void dfs(int col, char[][] board, List<List<String>> res) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (validate(board, row, col)) {
                board[row][col] = 'Q';           // Place queen
                dfs(col + 1, board, res);        // Recurse to next column
                board[row][col] = '.';           // Backtrack
            }
        }
    }

    // Check if it's valid to place a queen at board[row][col]
    private boolean validate(char[][] board, int row, int col) {
        int n = board.length;

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        // Check left row
        for (int j = col; j >= 0; j--)
            if (board[row][j] == 'Q') return false;

        // Check lower-left diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--)
            if (board[i][j] == 'Q') return false;

        return true;
    }

    // Construct the current board configuration into list of strings
    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board)
            result.add(new String(row));
        return result;
    }

    /*
    ================================
    Optimized Backtracking Version
    ================================
    */
    public List<List<String>> solveNQueensOptimized(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(board[i], '.');

        List<List<String>> res = new ArrayList<>();

        // Arrays to track occupied rows and diagonals
        int[] leftRow = new int[n];
        int[] lowerDiagonal = new int[2 * n - 1];        // row + col
        int[] upperDiagonal = new int[2 * n - 1];        // n - 1 + col - row

        solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
        return res;
    }

    private void solve(int col, char[][] board, List<List<String>> res,
                       int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            // Check if placing a queen is valid using precomputed arrays
            if (leftRow[row] == 0 &&
                lowerDiagonal[row + col] == 0 &&
                upperDiagonal[board.length - 1 + col - row] == 0) {

                // Place queen
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[board.length - 1 + col - row] = 1;

                // Recurse for next column
                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);

                // Backtrack
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    /*
    ================================
    Driver code to test both versions
    ================================
    */
    public static void main(String[] args) {
        NQueens sol = new NQueens();
        int n = 4;

        System.out.println("Naive Backtracking Results:");
        List<List<String>> naiveResults = sol.solveNQueens(n);
        for (List<String> board : naiveResults)
            System.out.println(board);

        System.out.println("\nOptimized Backtracking Results:");
        List<List<String>> optimizedResults = sol.solveNQueensOptimized(n);
        for (List<String> board : optimizedResults)
            System.out.println(board);
    }
}
