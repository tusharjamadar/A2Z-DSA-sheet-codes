package StackAndQueue.InfixPostfixPrefix;
import java.util.Stack;

/*
 * Problem:
 * Convert a postfix expression to a prefix expression.
 * 
 * Postfix: operands before operator (e.g., "ab+")
 * Prefix: operator before operands (e.g., "+ab")
 *
 * Example:
 * Input: "ab+"
 * Output: "+ab"
 */

/*
 * Intuition:
 * In postfix, the operator follows two operands.
 * To convert it to prefix, we reverse the format: operator first, then operands.
 * 
 * For every character:
 *  - If it's an operand, push it onto the stack.
 *  - If it's an operator, pop top two elements from stack (these are operands),
 *    and push a new string: operator + operand1 + operand2.
 */

/*
 * Algorithm:
 * 1. Initialize an empty stack.
 * 2. Traverse the postfix expression from left to right.
 *    a. If the character is an operand, push it to the stack.
 *    b. If the character is an operator:
 *       - Pop the top two operands from the stack.
 *       - Combine into new string: operator + operand1 + operand2
 *       - Push this string back on the stack.
 * 3. Final result will be on top of the stack.
 */

/*
 * Time Complexity: O(n), where n is the length of the expression.
 * Space Complexity: O(n), for the stack storage.
 */

public class PostfixToPrefix {

    static String postToPre(String post_exp) {
        Stack<String> st = new Stack<>();  // Stack to hold partial expressions

        for (int i = 0; i < post_exp.length(); i++) {
            char ch = post_exp.charAt(i);

            // If character is an operand, push to stack
            if (Character.isLetterOrDigit(ch)) {
                st.push(Character.toString(ch));
            } else {
                // Operator encountered
                // Pop the top two operands
                String t1 = st.pop();  // Right operand
                String t2 = st.pop();  // Left operand

                // Form prefix expression and push
                String temp = ch + t2 + t1;
                st.push(temp);
            }
        }

        return st.peek();  // Final result
    }

    public static void main(String[] args) {
        String postfix1 = "ab+";
        String postfix2 = "abc*+";
        String postfix3 = "abcd^e-fgh*+^*+i-";

        System.out.println("Postfix: " + postfix1 + " -> Prefix: " + postToPre(postfix1));
        System.out.println("Postfix: " + postfix2 + " -> Prefix: " + postToPre(postfix2));
        System.out.println("Postfix: " + postfix3 + " -> Prefix: " + postToPre(postfix3));
    }
}
