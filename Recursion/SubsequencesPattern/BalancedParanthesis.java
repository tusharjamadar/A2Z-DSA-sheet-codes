package SubsequencesPattern;
import java.util.ArrayList;
import java.util.List;

class BalancedParanthesis {
    /*
    ✅ Problem: Generate Parentheses

    ✅ Problem Description:
    Given `n` pairs of parentheses, generate all possible combinations of well-formed parentheses.

    A well-formed parentheses string means:
    - Every opening '(' has a matching closing ')'
    - At any point in the string, the number of ')' should not exceed the number of '(' before it

    ✅ Intuition:
    - Use backtracking to try every possible valid placement of '(' and ')'
    - Keep track of how many opening and closing parentheses are left to use

    ✅ Algorithm:
    1. Initialize the result list.
    2. Call the recursive function with:
       - op = n → number of opening brackets left to place
       - cl = n → number of closing brackets left to place
       - str = current string being formed
    3. At each step:
       - If you can place an opening bracket, place it and recurse.
       - If you can place a closing bracket (only if cl > op), place it and recurse.
    4. If both `op` and `cl` reach 0, add the valid string to the result list.

    ✅ Time Complexity:
    - O(2^2n) in worst case due to recursive branching
    - Actual number of valid combinations is the Catalan number C(n) = (2n)! / ((n+1)! * n!)
    - Space Complexity: O(2n) for recursion + output list
    */

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>(); // Result list to store valid parentheses combinations
        int op = n, cl = n;                   // `op` = open brackets left, `cl` = close brackets left
        String str = "";                      // Temporary string used during backtracking

        solve(op, cl, str, res);              // Start backtracking

        return res;
    }

    // ✅ Recursive helper function to build valid parentheses strings
    public static void solve(int op, int cl, String str, List<String> res){
        // ✅ Base Case: If both opening and closing brackets are used up, add string to result
        if(op == 0 && cl == 0){
            res.add(str);
            return;
        }

        // ✅ Choice 1: Place an opening bracket if available
        if(op != 0){
            solve(op - 1, cl, str + '(', res);
        }

        // ✅ Choice 2: Place a closing bracket only if it will not lead to invalid string
        // i.e., only place ')' if more closing brackets are left than opening (to balance)
        if(cl > op){
            solve(op, cl - 1, str + ')', res);
        }

        return;
    }

    // 🔍 Example test
    public static void main(String[] args) {
        BalancedParanthesis sol = new BalancedParanthesis();
        System.out.println("n = 3 -> " + sol.generateParenthesis(3));
        System.out.println("n = 1 -> " + sol.generateParenthesis(1));
    }
}

