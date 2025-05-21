package StackAndQueue.Learning;
import java.util.Stack;

public class ValidParentheses {

    /*
     * Problem:
     * LeetCode 20: Valid Parentheses
     * 
     * Given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * 
     * A string is valid if:
     * 1. Open brackets must be closed by the same type of brackets.
     * 2. Open brackets must be closed in the correct order.
     * 3. Every close bracket must have a corresponding open bracket of the same type.
     * 
     * Example 1:
     * Input: s = "()"
     * Output: true
     * 
     * Example 2:
     * Input: s = "()[]{}"
     * Output: true
     * 
     * Example 3:
     * Input: s = "(]"
     * Output: false
     * 
     * Example 4:
     * Input: s = "([])"
     * Output: true
     * 
     * Constraints:
     * - 1 <= s.length <= 10^4
     * - s consists only of '(', ')', '{', '}', '[' and ']'
     */

    /*
     * Intuition:
     * Use a stack to keep track of opening brackets.
     * For each closing bracket, check if it matches the top of the stack.
     * If it doesn’t match or the stack is empty, the string is invalid.
     * If the stack is empty after processing the entire string, it is valid.
     */

    /*
     * Algorithm:
     * 1. Initialize an empty stack.
     * 2. Iterate through each character in the string:
     *    - If it's an opening bracket, push it onto the stack.
     *    - If it's a closing bracket:
     *       - If the stack is empty, return false.
     *       - Pop the top element and check if it matches with the current closing bracket.
     *       - If it doesn’t match, return false.
     * 3. After processing all characters, return true only if the stack is empty.
     */

    /*
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(n), in the worst case all characters are pushed onto the stack.
     */

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // If stack is empty or brackets do not match, return false
                if (stack.isEmpty()) return false;

                char open = stack.pop();
                if (!isMatchingPair(open, ch)) return false;
            }
        }

        // If stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }

    // Helper method to check if the open and close brackets form a valid pair
    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
