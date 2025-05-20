package BitManipulation.AdvancedMaths;

/*
Problem:
Given an integer n, return the number of prime numbers that are strictly less than n.

Example:
Input: n = 10
Output: 4
Explanation: Prime numbers less than 10 are 2, 3, 5, 7

Constraints:
0 <= n <= 5 * 10^6
Expected Time Complexity: O(n log log n)
Expected Space Complexity: O(n)
*/

class CountPrimes {

    public int countPrimes(int n) {
        // Edge case: No primes less than 2
        if (n <= 2) return 0;

        // Sieve of Eratosthenes initialization
        boolean[] isNotPrime = new boolean[n]; // false means prime
        int count = 0;

        // Start from 2 to sqrt(n)
        for (int i = 2; i * i < n; i++) {
            if (!isNotPrime[i]) {
                // Mark all multiples of i as not prime
                for (int j = i * i; j < n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        // Count all primes less than n
        for (int i = 2; i < n; i++) {
            if (!isNotPrime[i]) count++;
        }

        return count;
    }

    // Optional: Main method for quick testing
    public static void main(String[] args) {
        CountPrimes sol = new CountPrimes();
        System.out.println(sol.countPrimes(10)); // Output: 4
        System.out.println(sol.countPrimes(0));  // Output: 0
        System.out.println(sol.countPrimes(1));  // Output: 0
        System.out.println(sol.countPrimes(100)); // Output: 25
    }
}

/*
💡 Intuition:
We use the Sieve of Eratosthenes to find all primes less than `n` efficiently by marking the multiples of each prime as non-prime.

⚙️ Algorithm:
1. Initialize a boolean array of size n. False means prime.
2. Iterate from 2 to sqrt(n):
   - If the number is still marked as prime, mark all its multiples as non-prime.
3. Count all numbers that are still marked as prime.

⏱️ Time Complexity: O(n log log n)
🧠 Space Complexity: O(n)
*/
