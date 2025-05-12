package Basic;

class CountGoodNumbers {
    // ✅ Constant for modulo operation as the result can be large
    static final long MOD = 1_000_000_007;

    /*
    ✅ Problem: Count Good Numbers

    ✅ Problem Details:
    A string of digits (0-9) is considered "good" if:
    - Digits at even indices (0-based) are even => [0, 2, 4, 6, 8] → 5 options
    - Digits at odd indices are prime => [2, 3, 5, 7] → 4 options

    Given a length `n`, return the count of good digit strings of that length, modulo 10^9 + 7.

    ✅ Intuition:
    - For every even index (0, 2, 4, ...), we can choose any of 5 even digits.
    - For every odd index (1, 3, 5, ...), we can choose any of 4 prime digits.
    - So, total combinations = (5 ^ even positions) * (4 ^ odd positions)

    ✅ Algorithm:
    1. Count number of even indices = (n + 1) / 2
    2. Count number of odd indices = n / 2
    3. Use fast exponentiation to compute:
       - 5 ^ evenCount
       - 4 ^ oddCount
    4. Multiply both values and return result modulo 10^9 + 7

    ✅ Time Complexity:
    - O(log n) due to binary exponentiation
    - Space Complexity: O(1)
    */

    public int countGoodNumbers(long n) {
        // Number of even positions in the string (0-based indexing)
        long evenCount = (n + 1) / 2;
        // Number of odd positions
        long oddCount = n / 2;

        // Compute 5 ^ evenCount % MOD
        long evenPow = power(5, evenCount);
        // Compute 4 ^ oddCount % MOD
        long oddPow = power(4, oddCount);

        // Multiply the results and return the answer modulo MOD
        return (int)((evenPow * oddPow) % MOD);
    }

    /*
    ✅ power(base, exp):
    Computes (base ^ exp) % MOD using binary exponentiation

    ✅ Why use binary exponentiation?
    - To compute large powers efficiently in O(log exp) time
    - Handles large values by breaking down the power into binary bits
    */
    public static long power(long base, long exp) {
        long ans = 1;
        base %= MOD;

        while (exp > 0) {
            // If current bit of exponent is 1, multiply base
            if (exp % 2 == 1) {
                ans = (ans * base) % MOD;
                exp--;
            } else {
                // Square the base and halve the exponent
                base = (base * base) % MOD;
                exp = exp / 2;
            }
        }

        return ans;
    }

    // 🔍 Example test
    public static void main(String[] args) {
        CountGoodNumbers sol = new CountGoodNumbers();

        System.out.println("n = 1 => " + sol.countGoodNumbers(1));     // Output: 5
        System.out.println("n = 4 => " + sol.countGoodNumbers(4));     // Output: 400
        System.out.println("n = 50 => " + sol.countGoodNumbers(50));   // Output: 564908303
    }
}
