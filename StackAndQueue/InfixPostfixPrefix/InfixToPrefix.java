package StackAndQueue.InfixPostfixPrefix;

import java.util.Stack;

public class InfixToPrefix {

    /*
     * Problem:
     * Convert a given infix expression (e.g., a+b*c) to prefix expression (e.g., +a*bc).
     * Infix: operator between operands (a + b)
     * Prefix: operator before operands (+ a b)
     * 
     * Precedence (high to low): ^ > * = / > + = -
     * ^ is treated as **right-associative** (higher precedence operators evaluated first).
     * 
     * Examples:
     * Input: "a+b*(c^d-e)^(f+g*h)-i"
     * Output: "-+a*b^-^cde+f*ghi"
     */

    /*
     * Intuition:
     * Prefix conversion is trickier than postfix, so we use the trick:
     * 1. Reverse the infix string
     * 2. Swap '(' with ')' and vice versa
     * 3. Convert the reversed expression to postfix
     * 4. Reverse the result to get prefix
     * 
     * This works because the order of operations in prefix is essentially a reversed version of postfix.
     */

    /*
     * Algorithm:
     * - Reverse infix expression
     * - Swap '(' <--> ')'
     * - Use a stack to convert to postfix (similar to infix-to-postfix algorithm)
     *   - Operand: add to output
     *   - '(': push to stack
     *   - ')': pop to output until '('
     *   - Operator:
     *     - While top of stack has greater or equal precedence and not '^' (right-associative), pop to output
     *     - Push operator to stack
     * - Pop any remaining operators from the stack
     * - Reverse the output to get prefix
     */

    /*
     * Time Complexity: O(n), where n is the length of the input string
     * Space Complexity: O(n), due to the use of stack and result string
     */

    // Helper function to reverse the string and swap '(' with ')'
    public static String reverseString(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') sb.append(')');
            else if (ch == ')') sb.append('(');
            else sb.append(ch);
        }
        return sb.toString();
    }

    // Function to get operator precedence
    public static int priority(char ch) {
        switch (ch) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return -1;
        }
    }

    // Main function to convert infix to prefix
    public static String infixToPrefix(String s) {
        // Step 1: Reverse and swap brackets
        s = reverseString(s);

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        // Step 2: Convert reversed infix to postfix
        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch); // Operand goes directly to result
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Remove '(' from stack
                }
            } else {
                // Operator handling
                while (!stack.isEmpty() &&
                        (priority(ch) < priority(stack.peek()) ||
                        (priority(ch) == priority(stack.peek()) && ch != '^'))) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // Step 3: Reverse the postfix result to get prefix
        return result.reverse().toString();
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        String s = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Prefix: " + infixToPrefix(s)); // Expected: -+a*b^-^cde+f*ghi
    }
}
