package Hard;

/*
Problem Description:
Given a numeric string `num` and a target value `target`, you need to add binary operators '+', '-', or '*' between digits of `num`
so that the expression evaluates to the target. Return all such valid expressions. Note that numbers in expressions should not have leading zeros.

Intuition:
We perform backtracking to try every possible split and insertion of operators between digits.
At each step, we build a number (substring) and choose to either:
- Start a new expression with it (only at position 0),
- Add it with '+' or '-',
- Multiply with '*' (requires special handling due to precedence).

Algorithm:
1. Use a recursive helper function `solve()` to try all combinations.
2. At each step, iterate from the current position to the end of the string and extract substrings.
3. Skip invalid numbers with leading zeros.
4. For each number, if it's the starting number, call recursion without any operator.
5. Otherwise, recursively try '+', '-', and '*' with appropriate evaluation and tracking.
6. Track:
   - The current expression path,
   - The cumulative evaluation so far,
   - The last multiplied value (for proper '*' evaluation).

Time Complexity:
O(4^n), where n is the length of the string `num`. Each digit can be part of a number or followed by an operator.

Space Complexity:
O(n) for the recursion stack and path construction.

*/

import java.util.*;

class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;

        // Start backtracking from position 0
        solve(res, "", num, target, 0, 0, 0);
        return res;
    }

    // Helper function to recursively build expressions
    private void solve(List<String> res, String path, String num, int target, int pos, long eval, long multed) {
        // Base case: if we have reached the end of the string
        if (pos == num.length()) {
            // If the evaluated result equals the target, add path to result
            if (target == eval) {
                res.add(path);
            }
            return;
        }

        // Try all possible splits of the string
        for (int i = pos; i < num.length(); i++) {
            // Skip numbers with leading zeros
            if (i != pos && num.charAt(pos) == '0') break;

            // Convert current substring to number
            long cur = Long.parseLong(num.substring(pos, i + 1));

            if (pos == 0) {
                // First number in expression, no operator needed
                solve(res, path + cur, num, target, i + 1, cur, cur);
            } else {
                // Try '+' operator
                solve(res, path + "+" + cur, num, target, i + 1, eval + cur, cur);

                // Try '-' operator
                solve(res, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                // Try '*' operator, handle multiplication precedence
                solve(res, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }

    // Main function to test the solution
    public static void main(String[] args) {
        ExpressionAddOperators sol = new ExpressionAddOperators();

        String num1 = "123";
        int target1 = 6;
        System.out.println("Expressions for num = \"123\", target = 6:");
        System.out.println(sol.addOperators(num1, target1)); // Output: ["1+2+3", "1*2*3"]

        String num2 = "232";
        int target2 = 8;
        System.out.println("Expressions for num = \"232\", target = 8:");
        System.out.println(sol.addOperators(num2, target2)); // Output: ["2*3+2", "2+3*2"]

        String num3 = "3456237490";
        int target3 = 9191;
        System.out.println("Expressions for num = \"3456237490\", target = 9191:");
        System.out.println(sol.addOperators(num3, target3)); // Output: []
    }
}
