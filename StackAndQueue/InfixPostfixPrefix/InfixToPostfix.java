package StackAndQueue.InfixPostfixPrefix;

import java.util.Stack;

public class InfixToPostfix {

    /*
     * Problem:
     * Convert a given infix expression (e.g., a+b*c) to postfix expression (e.g., abc*+).
     * 
     * Infix: operators appear between operands (a + b)
     * Postfix: operators appear after operands (a b +)
     * 
     * Precedence (from highest to lowest): ^ > * = / > + = -
     * Note: ^ is treated as left-associative here (though it's normally right-associative).
     * 
     * Examples:
     * Input:  "a+b*(c^d-e)^(f+g*h)-i"
     * Output: "abcd^e-fgh*+^*+i-"
     * 
     * Input: "A*(B+C)/D"
     * Output: "ABC+*D/"
     * 
     * Constraints:
     * 1 <= s.length <= 30
     */

    /*
     * Intuition:
     * Use a stack to hold operators and build the postfix string:
     * - If it's an operand (letter or digit), add it to the output.
     * - If it's an '(', push it onto the stack.
     * - If it's a ')', pop from the stack to output until '(' is found.
     * - For an operator, pop from stack to output until top of stack has lower precedence, then push current operator.
     * - After processing all characters, pop remaining stack content to the output.
     */

    /*
     * Algorithm:
     * 1. Loop through the characters in the string:
     *    - Operand: append to result.
     *    - '(': push to stack.
     *    - ')': pop and append until '(' is found.
     *    - Operator: while top of stack has greater or equal precedence, pop and append, then push operator.
     * 2. After the loop, pop all remaining operators to the result.
     */

    /*
     * Time Complexity: O(n), where n is the length of the expression.
     * Space Complexity: O(n), for the stack and output string.
     */

    // Function to return precedence of operators
    public static int priority(char ch) {
        if (ch == '^') {
            return 3;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '+' || ch == '-') {
            return 1;
        } else {
            return -1;
        }
    }

    // Function to convert infix to postfix
    public static String infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If operand, directly add to output
            if ((ch >= 'a' && ch <= 'z') ||
                (ch >= 'A' && ch <= 'Z') ||
                (ch >= '0' && ch <= '9')) {
                ans.append(ch);
            }
            // If opening parenthesis, push to stack
            else if (ch == '(') {
                st.push(ch);
            }
            // If closing parenthesis, pop till '('
            else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    ans.append(st.pop());
                }
                st.pop(); // pop '('
            }
            // Operator: pop higher or equal precedence operators from stack
            else {
                while (!st.isEmpty() && priority(ch) <= priority(st.peek())) {
                    ans.append(st.pop());
                }
                st.push(ch);
            }
        }

        // Pop remaining operators from stack
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.toString();
    }

    // Optional: You can include a main() method to test the implementation
    public static void main(String[] args) {
        String s = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Postfix: " + infixToPostfix(s)); // Output: abcd^e-fgh*+^*+i-
    }
}
