package Greedy.Medium;

/*
✅ Problem Statement:
------------------------
Given a string `s` containing only '(', ')' and '*', determine if it's valid.

Rules for validity:
- '(' must be closed by ')'
- ')' must be opened by '('
- '(' must appear before its corresponding ')'
- '*' can be '(', ')' or an empty string ""

------------------------
✅ Intuition:
We keep track of a range of possible unmatched '(' counts using:
- `min`: Minimum number of unmatched '(' considering '*' as ')'
- `max`: Maximum number of unmatched '(' considering '*' as '('

At any point:
- If `max` < 0 → too many ')' → return false
- At the end: If `min` == 0 → all open brackets matched correctly → return true

------------------------
✅ Step-by-step Algorithm:
1. Initialize two counters: `min = 0`, `max = 0`
2. Traverse the string:
   - If char == '(': increment both min and max
   - If char == ')': decrement both min and max
   - If char == '*': decrement min, increment max (wildcard flexibility)
3. Clamp `min` to 0 if it drops below
4. If `max` < 0 → invalid state → return false
5. After traversal, if `min == 0`, return true, else false

------------------------
✅ Time Complexity: O(N)
Where N = length of string `s`

✅ Space Complexity: O(1)
Only constant extra space used

*/

class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int min = 0, max = 0; // Tracks min/max unmatched '('

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                min++; max++;
            } else if (ch == ')') {
                min--; max--;
            } else { // ch == '*'
                min--; max++;
            }

            if (min < 0) min = 0; // '*' can compensate an unmatched ')'

            if (max < 0) return false; // More ')' than possible '('s
        }

        return min == 0;
    }

    // ✅ Main method for testing
    public static void main(String[] args) {
        ValidParenthesisString sol = new ValidParenthesisString();

        System.out.println(sol.checkValidString("()"));      // true
        System.out.println(sol.checkValidString("(*)"));     // true
        System.out.println(sol.checkValidString("(*))"));    // true
        System.out.println(sol.checkValidString("(((**)"));  // true
        System.out.println(sol.checkValidString("(((*)"));   // false
    }
}
