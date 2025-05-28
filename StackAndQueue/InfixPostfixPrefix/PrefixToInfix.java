package StackAndQueue.InfixPostfixPrefix;
import java.util.Stack;

/*
 * Problem:
 * Convert a prefix expression to an infix expression.
 * 
 * Prefix: operator before operands (e.g., "+ab")
 * Infix: operands between operators (e.g., "a+b")
 *
 * Example:
 * Input: "+ab"
 * Output: "(a+b)"
 */

/*
 * Intuition:
 * In prefix notation, operators come before their operands.
 * We need to process the string from right to left.
 * 
 * When we see an operand, push it onto a stack.
 * When we see an operator, pop two operands from the stack,
 * form an infix expression "(operand1 operator operand2)",
 * and push the resulting string back onto the stack.
 */

/*
 * Algorithm:
 * 1. Initialize an empty stack.
 * 2. Traverse the prefix expression from right to left.
 *    a. If the character is an operand, push it to the stack.
 *    b. If the character is an operator:
 *       - Pop top two elements from the stack (these are operands).
 *       - Combine them into a new string with parentheses.
 *       - Push the combined string back to the stack.
 * 3. The final item on the stack is the resulting infix expression.
 */

/*
 * Time Complexity: O(n), where n is the length of the expression.
 * Space Complexity: O(n), due to the stack used.
 */

public class PrefixToInfix {

    static String preToInfix(String pre_exp) {
        Stack<String> st = new Stack<>();  // Stack to hold partial expressions

        // Traverse the prefix expression from right to left
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);

            // If character is an operand, push to stack as a string
            if (Character.isLetterOrDigit(ch)) {
                st.push(Character.toString(ch));
            } else {
                // Operator encountered
                // Pop the top two operands
                String t1 = st.pop(); // Left operand
                String t2 = st.pop(); // Right operand

                // Create infix expression and push back to stack
                String temp = "(" + t1 + ch + t2 + ")";
                st.push(temp);
            }
        }

        // Final expression in the stack is the result
        return st.peek();
    }

    public static void main(String[] args) {
        String prefix1 = "+ab";
        String prefix2 = "+a*bc";
        String prefix3 = "-+a*b^-^cde+f*ghi";  // Equivalent to: a+b*(c^d-e)^(f+g*h)-i

        System.out.println("Prefix: " + prefix1 + " -> Infix: " + preToInfix(prefix1));
        System.out.println("Prefix: " + prefix2 + " -> Infix: " + preToInfix(prefix2));
        System.out.println("Prefix: " + prefix3 + " -> Infix: " + preToInfix(prefix3));
    }
}
