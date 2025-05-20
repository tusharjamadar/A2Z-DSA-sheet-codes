package BitManipulation.AdvancedMaths;
/*
Problem:
Given a number N, return all its **unique** prime factors in **increasing order**.

Example:
Input: N = 100
Output: [2, 5]
Explanation: 100 = 2^2 * 5^2

Constraints:
1 <= N <= 10^7
Expected Time Complexity: O(sqrt(N))
Expected Auxiliary Space: O(1)
*/

/*
💡 Intuition:
- A number can be broken down into prime factors.
- We use trial division from 2 to sqrt(N).
- If a number is divisible, we divide it fully and mark it as a prime factor once.
- If anything is left > 1 after the loop, it's also a prime factor.

⚙️ Algorithm:
1. Initialize empty list for result.
2. Iterate from 2 to sqrt(N):
   - If i divides N:
     - Add i to result.
     - Divide N completely by i.
3. If N > 1 at the end, add it to result.
4. Convert list to array and sort.

⏱️ Time Complexity: O(sqrt(N))
🧠 Space Complexity: O(1) excluding output storage
*/


import java.util.*;

class CountPrimes {

    // Function to return array of unique prime factors of n in sorted order
    public int[] AllPrimeFactors(int n) {
        ArrayList<Integer> res = new ArrayList<>();

        // Check for divisibility from 2 to sqrt(n)
        for (int i = 2; i * i <= n; i++) {
            // If divisible, it's a prime factor
            if (n % i == 0) {
                res.add(i);
                // Remove all occurrences of this factor
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        // If n is still > 1, it must be a prime factor itself
        if (n > 1) {
            res.add(n);
        }

        // Convert to array
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        Arrays.sort(ans); // Ensure increasing order
        return ans;
    }

    // Main method to test the function
    public static void main(String[] args) {
        CountPrimes sol = new CountPrimes();

        System.out.println(Arrays.toString(sol.AllPrimeFactors(100))); // Output: [2, 5]
        System.out.println(Arrays.toString(sol.AllPrimeFactors(35)));  // Output: [5, 7]
        System.out.println(Arrays.toString(sol.AllPrimeFactors(13)));  // Output: [13]
        System.out.println(Arrays.toString(sol.AllPrimeFactors(1)));   // Output: []
    }
}

