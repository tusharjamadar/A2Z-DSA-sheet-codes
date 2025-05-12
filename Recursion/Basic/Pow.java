package Basic;
public class Pow {

    /*
    ✅ Problem: Pow(x, n)

    ✅ Problem Details:
    Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
    Constraints:
    - -100.0 < x < 100.0
    - -2^31 <= n <= 2^31 - 1
    - x ≠ 0 when n < 0

    ✅ Intuition:
    Repeated multiplication of x, n times would work but is too slow for large n.
    Instead, use the method of "Exponentiation by Squaring" to reduce time complexity from O(n) to O(log n).

    ✅ Idea of Exponentiation by Squaring:
    - x^n = (x^(n/2))^2 if n is even
    - x^n = x * (x^(n/2))^2 if n is odd
    This divides the problem into subproblems of size n/2, which gives us log(n) complexity.
    */

    // Recursive implementation
    public double powerRecursive(double x, long n) {
        // Base case: x^0 = 1
        if (n == 0) return 1;

        // Recursive call to calculate x^(n/2)
        double half = powerRecursive(x, n / 2);

        // If exponent is even, return half * half
        // If odd, multiply once more by x
        if (n % 2 == 0) return half * half;
        else return half * half * x;
    }

    public double myPowRecursive(double x, int n) {
        // Convert to long to handle edge case: n = -2^31
        long nn = n;

        // If exponent is negative, convert x to 1/x and make exponent positive
        if (nn < 0) {
            x = 1 / x;
            nn = -nn;
        }

        // Start recursion
        return powerRecursive(x, nn);
    }

    /*
    ✅ Iterative Version:
    Uses the same exponentiation by squaring technique, but avoids recursion to save stack space.

    ✅ Algorithm:
    - Convert n to long to avoid overflow
    - If n is negative, take reciprocal of x and negate n
    - Initialize result to 1
    - Loop while n > 0:
        - If n is odd, multiply result with x and decrement n
        - If n is even, square x and halve n
    - Return result
    */

    // Iterative implementation
    public double myPowIterative(double x, int n) {
        double ans = 1.0;
        long nn = n;

        // Convert negative exponent to positive
        if (nn < 0) {
            x = 1 / x;
            nn = -nn;
        }

        // Loop while exponent is not zero
        while (nn > 0) {
            // If exponent is odd, multiply result with x and reduce exponent
            if (nn % 2 == 1) {
                ans = ans * x;
                nn = nn - 1;
            } else {
                // If even, square x and halve exponent
                x = x * x;
                nn = nn / 2;
            }
        }

        return ans;
    }

    // Main method to test both implementations
    public static void main(String[] args) {
        Pow sol = new Pow();

        double[][] testCases = {
            {2.0, 10},
            {2.1, 3},
            {2.0, -2},
            {0.5, -3},
            {-2.0, 5},
            {-2.0, 6}
        };

        System.out.println("Testing Recursive Version:");
        for (double[] testCase : testCases) {
            double x = testCase[0];
            int n = (int) testCase[1];
            System.out.printf("Input: x = %.5f, n = %d => Output: %.5f\n", x, n, sol.myPowRecursive(x, n));
        }

        System.out.println("\nTesting Iterative Version:");
        for (double[] testCase : testCases) {
            double x = testCase[0];
            int n = (int) testCase[1];
            System.out.printf("Input: x = %.5f, n = %d => Output: %.5f\n", x, n, sol.myPowIterative(x, n));
        }
    }
}
