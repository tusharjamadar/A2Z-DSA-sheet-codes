package StackAndQueue.MonotonicStackQueue;
import java.util.*;

class RemoveKDigits {

    /*
     * 402. Remove K Digits
     *
     * 🧩 Problem:
     * Given a string num representing a non-negative integer and an integer k,
     * remove k digits from the number so that the new number is the smallest possible.
     * 
     * Return the resulting number as a string.
     * 
     * ------------------------------------------------------------------
     * 💡 Intuition:
     * - To get the smallest number, remove digits that are **larger and to the left** of a smaller digit.
     * - This is similar to maintaining a **monotonic increasing sequence**.
     * - Use a stack (or deque) to build the result:
     *     - While the current digit is smaller than the top of the stack and we still have `k` removals left,
     *       pop from the stack (remove the larger digit).
     *     - Push current digit onto the stack.
     * - After traversing all digits, remove any remaining digits from the end (if `k > 0`).
     * - Finally, construct the number from the stack and remove any leading zeroes.
     * 
     * ------------------------------------------------------------------
     * 🧪 Algorithm:
     * 1. Use a Deque to store digits of the final number.
     * 2. Iterate through each digit in the string:
     *    - While k > 0 and the last digit in the deque is greater than the current digit, remove it.
     *    - Add current digit to the deque.
     * 3. If k > 0 after the loop, remove the last k digits from the deque.
     * 4. Build the final result from the deque:
     *    - Skip any leading zeros.
     *    - If result is empty after removing zeros, return "0".
     * 
     * ------------------------------------------------------------------
     * ⏱️ Time Complexity: O(n)
     * - Each digit is pushed and popped from the deque at most once.
     * 
     * 🗃️ Space Complexity: O(n)
     * - For storing digits in the deque and final result.
     */

    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char digit : num.toCharArray()) {
            // Remove larger previous digits to get a smaller number
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > digit) {
                stack.pollLast();
                k--;
            }
            stack.addLast(digit);
        }

        // Remove any remaining digits from the end
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        // Build final number, skipping leading zeros
        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;

        for (char digit : stack) {
            if (leadingZero && digit == '0') continue;
            leadingZero = false;
            result.append(digit);
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    // 🔍 Main method to test the implementation
    public static void main(String[] args) {
        RemoveKDigits sol = new RemoveKDigits();

        String num1 = "1432219";
        int k1 = 3;
        System.out.println(sol.removeKdigits(num1, k1)); // Output: "1219"

        String num2 = "10200";
        int k2 = 1;
        System.out.println(sol.removeKdigits(num2, k2)); // Output: "200"

        String num3 = "10";
        int k3 = 2;
        System.out.println(sol.removeKdigits(num3, k3)); // Output: "0"

        String num4 = "1234567890";
        int k4 = 9;
        System.out.println(sol.removeKdigits(num4, k4)); // Output: "0"
    }
}
