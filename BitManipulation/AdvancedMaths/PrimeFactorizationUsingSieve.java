package BitManipulation.AdvancedMaths;
/*
Problem:
Given a number N, return its prime factorization using a Sieve-based approach.

Example:
Input: N = 12246
Output: 2 3 13 157

Explanation: 2 * 3 * 13 * 157 = 12246

Constraints:
2 <= N <= 2 * 10^5
Expected Time Complexity: O(N log log N)
Expected Space Complexity: O(N)
*/

import java.util.ArrayList;
import java.util.List;

class PrimeFactorizationUsingSieve {
    static int MAX = 200000;
    static int[] spf = new int[MAX + 1]; // Smallest prime factor

    // 💡 Build Sieve to store the smallest prime factor (SPF) for every number
    static void sieve() {
        for (int i = 1; i <= MAX; i++) spf[i] = i;

        for (int i = 2; i * i <= MAX; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i; // Mark the smallest prime factor
                    }
                }
            }
        }
    }

    // ⚙️ Function to find prime factors using precomputed SPF array
    static List<Integer> findPrimeFactors(int N) {
        // Make sure sieve is initialized
        if (spf[1] == 0) sieve();

        List<Integer> res = new ArrayList<>();
        while (N > 1) {
            int prime = spf[N];
            res.add(prime);
            N /= prime;
        }
        return res;
    }

    // Optional: main method for testing
    public static void main(String[] args) {
        List<Integer> factors = findPrimeFactors(12246);
        for (int val : factors) {
            System.out.print(val + " ");
        }
        // Output: 2 3 13 157
    }
}

/*
💡 Intuition:
We use the Sieve of Eratosthenes not only to mark prime numbers, but to also record the **smallest prime factor (SPF)** for each number.
Then, to factorize any number N, we can keep dividing N by its SPF until it becomes 1.

⚙️ Algorithm:
1. Precompute SPF for all numbers up to MAX using a modified sieve.
2. For a given number N:
   - Repeatedly divide N by spf[N] and add spf[N] to the result list.

⏱️ Time Complexity:
- Sieve: O(N log log N)
- Factorization of N: O(log N)

📦 Space Complexity:
- O(N) for the SPF array
*/
