package String.Easy;
/*
Problem: 1021. Remove Outermost Parentheses

Problem Details:
Given a valid parentheses string `s`, return the string after removing the outermost parentheses 
of every primitive valid substring in its decomposition.

A primitive valid parentheses string is one that is non-empty and cannot be split into two non-empty valid parentheses strings.

Intuition:
We track the depth of the parentheses using a balance counter.
If the depth is more than 1, it means we are inside a primitive, so we include the character.
We skip adding the outermost '(' and ')' of each primitive by checking balance before and after modifying it.

Algorithm:
1. Initialize a StringBuilder to build the result and an integer `balance` to track open '('.
2. Traverse each character in the string:
    - If it's '(', increase balance. If balance > 1, it's not the outermost, add it to result.
    - If it's ')', decrease balance. If balance > 0 after decrement, it's not the outermost, add it to result.
3. Return the final result.

Time Complexity:
O(n) - We iterate over the string once where n = s.length().
*/

class RemoveOuterMostParenthesis {
    public String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int balance = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (balance > 0) {
                    res.append(ch); // Append only if not outermost
                }
                balance++;
            } else {
                balance--;
                if (balance > 0) {
                    res.append(ch); // Append only if not outermost
                }
            }
        }

        return res.toString();
    }

    // Main method to test the solution
    public static void main(String[] args) {
        RemoveOuterMostParenthesis sol = new RemoveOuterMostParenthesis();

        String s1 = "(()())(())";
        String s2 = "(()())(())(()(()))";
        String s3 = "()()";

        System.out.println("Output 1: " + sol.removeOuterParentheses(s1)); // Expected: "()()()"
        System.out.println("Output 2: " + sol.removeOuterParentheses(s2)); // Expected: "()()()()(())"
        System.out.println("Output 3: " + sol.removeOuterParentheses(s3)); // Expected: ""
    }
}
