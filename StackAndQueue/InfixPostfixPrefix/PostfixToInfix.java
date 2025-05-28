package StackAndQueue.InfixPostfixPrefix;
/*
 * Problem:
 * Convert a postfix expression to an infix expression.
 * Postfix: operands followed by operator (e.g., "ab+" => a + b)
 * Infix: operands between operators (e.g., a + b)
 *
 * Examples:
 * Input: "ab+"
 * Output: "(a+b)"
 * 
 * Input: "abc*+"
 * Output: "(a+(b*c))"
 */

import java.util.Stack;

/*
 * Intuition:
 * In postfix expressions, every time we encounter an operator, it must apply to the
 * two most recent operands. So we can use a stack to keep track of the operands.
 * 
 * When we hit an operator, we pop the top two operands from the stack, form a string
 * with parentheses around them and the operator in between, then push it back to the stack.
 * 
 * At the end, the stack will contain the complete infix expression.
 */

/*
 * Algorithm:
 * 1. Initialize an empty stack.
 * 2. Traverse the postfix expression from left to right.
 *    a. If the current character is an operand (letter/digit), push it as a string.
 *    b. If it's an operator:
 *       - Pop the top two elements from the stack.
 *       - Combine them as "(operand2 operator operand1)".
 *       - Push the resulting expression back to the stack.
 * 3. After traversal, the top of the stack contains the full infix expression.
 */

/*
 * Time Complexity: O(n), where n is the length of the expression.
 * Space Complexity: O(n), due to the stack used for storing partial expressions.
 */

 class PostfixToInfix {
    static String postToInfix(String exp) {
        Stack<String> st = new Stack<>();  // Stack to hold partial expressions
        
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            
            // If character is an operand, push to stack as a string
            if (Character.isLetterOrDigit(ch)) {
                st.push(Character.toString(ch));
            } else {
                // Operator encountered
                // Pop the top two operands
                String t1 = st.pop(); // Right operand
                String t2 = st.pop(); // Left operand
                
                // Create infix expression and push back to stack
                String temp = "(" + t2 + ch + t1 + ")";
                st.push(temp);
            }
        }
        
        // Final expression in the stack is the result
        return st.peek();
    }

    public static void main(String[] args) {
        String postfix1 = "ab+";
        String postfix2 = "abc*+";
        String postfix3 = "abcd^e-fgh*+^*+i-"; // From infix: a+b*(c^d-e)^(f+g*h)-i

        System.out.println("Postfix: " + postfix1 + " -> Infix: " + postToInfix(postfix1));
        System.out.println("Postfix: " + postfix2 + " -> Infix: " + postToInfix(postfix2));
        System.out.println("Postfix: " + postfix3 + " -> Infix: " + postToInfix(postfix3));
    }
}
