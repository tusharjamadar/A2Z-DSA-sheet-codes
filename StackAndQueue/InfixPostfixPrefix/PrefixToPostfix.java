package StackAndQueue.InfixPostfixPrefix;
import java.util.Stack;

/*
 * Problem:
 * Convert a prefix expression to a postfix expression.
 *
 * Prefix: operator before operands (e.g., "+ab")
 * Postfix: operands before operator (e.g., "ab+")
 *
 * Example:
 * Input: "+ab"
 * Output: "ab+"
 */

/*
 * Intuition:
 * In prefix, the operator comes before the two operands.
 * To convert to postfix, we traverse the prefix expression from **right to left**:
 *   - If the character is an operand, push it to stack.
 *   - If it's an operator, pop the top two elements from the stack (these are operands),
 *     then combine them in postfix form: operand1 + operand2 + operator, and push the result back.
 */

/*
 * Algorithm:
 * 1. Initialize an empty stack.
 * 2. Traverse the prefix string from right to left.
 *    a. If the character is an operand, push to the stack.
 *    b. If the character is an operator:
 *       - Pop the top two operands.
 *       - Form a postfix string: operand1 + operand2 + operator
 *       - Push the new string back to the stack.
 * 3. The final result will be on top of the stack.
 */

/*
 * Time Complexity: O(n), where n is the length of the expression.
 * Space Complexity: O(n), for the stack.
 */

public class PrefixToPostfix {

    static String preToPost(String pre_exp) {
        Stack<String> st = new Stack<>();  // Stack to hold partial expressions

        // Traverse from right to left
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                st.push(Character.toString(ch));
            } 
            // Operator
            else {
                String t1 = st.pop();  // Left operand
                String t2 = st.pop();  // Right operand

                // Combine into postfix format and push
                String temp = t1 + t2 + ch;
                st.push(temp);
            }
        }

        // Final result
        return st.peek();
    }

    public static void main(String[] args) {
        String prefix1 = "+ab";
        String prefix2 = "+a*bc";
        String prefix3 = "-+a*b^-^cde+f*ghi";

        System.out.println("Prefix: " + prefix1 + " -> Postfix: " + preToPost(prefix1));
        System.out.println("Prefix: " + prefix2 + " -> Postfix: " + preToPost(prefix2));
        System.out.println("Prefix: " + prefix3 + " -> Postfix: " + preToPost(prefix3));
    }
}
