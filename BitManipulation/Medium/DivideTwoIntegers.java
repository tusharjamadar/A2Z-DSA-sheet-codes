package BitManipulation.Medium;
/*
Problem Description:
Given two integers `dividend` and `divisor`, perform integer division without using multiplication, division, or modulus operators.
Return the quotient truncated toward zero.
Note: Clamp the result to the 32-bit signed integer range [-2^31, 2^31 - 1].

Intuition:
We simulate the division process using bit shifts (i.e., doubling the divisor) to speed up subtraction.
At each step, we subtract the largest shifted divisor from the dividend and keep adding powers of 2 to the result.
We handle negative signs and overflow cases explicitly.

Algorithm:
1. Handle overflow case: If dividend is Integer.MIN_VALUE and divisor is -1, return Integer.MAX_VALUE.
2. Handle special case where dividend == divisor → return 1.
3. Determine the sign of the result using the signs of dividend and divisor.
4. Convert both numbers to positive using `Math.abs` and cast to `long` to avoid overflow.
5. While the dividend is greater than or equal to the divisor:
   - Find the highest multiple of divisor (shifted) that fits into the current dividend.
   - Subtract that value from dividend and add the corresponding power of 2 to the quotient.
6. Apply the sign to the result and return the clamped result.

Time Complexity: O(log²N), where N is the absolute value of dividend. Inner loop shifts to find the maximum count.
Space Complexity: O(1), constant extra space.
*/

class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // Edge case: overflow condition
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Edge case: if both numbers are equal, result is 1
        if (dividend == divisor) return 1;

        // Determine the sign of the result
        boolean sign = true;
        if ((dividend >= 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = false; // result should be negative
        }

        // Convert both numbers to long and take absolute value to avoid overflow
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        // Keep subtracting the largest multiple of divisor from dividend
        while (n >= d) {
            int cnt = 0;

            // Find highest power such that (divisor * 2^cnt) <= dividend
            while (n >= (d << (cnt + 1))) {
                cnt++;
            }

            // Add the power of 2 to quotient
            quotient += 1L << cnt;

            // Subtract the shifted divisor from dividend
            n -= d << cnt;
        }

        // Clamp result if out of integer bounds
        if (quotient > Integer.MAX_VALUE) {
            return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        // Apply sign to the result
        return sign ? (int) quotient : (int) -quotient;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        DivideTwoIntegers sol = new DivideTwoIntegers();

        int dividend1 = 10, divisor1 = 3;
        System.out.println("10 / 3 = " + sol.divide(dividend1, divisor1)); // Output: 3

        int dividend2 = 7, divisor2 = -3;
        System.out.println("7 / -3 = " + sol.divide(dividend2, divisor2)); // Output: -2

        int dividend3 = Integer.MIN_VALUE, divisor3 = -1;
        System.out.println("Integer.MIN_VALUE / -1 = " + sol.divide(dividend3, divisor3)); // Output: Integer.MAX_VALUE

        int dividend4 = Integer.MIN_VALUE, divisor4 = 1;
        System.out.println("Integer.MIN_VALUE / 1 = " + sol.divide(dividend4, divisor4)); // Output: Integer.MIN_VALUE
    }
}
