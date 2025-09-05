package String.Hard;

/**
 * ✅ Problem: 686. Repeated String Match
 * ------------------------------------------------
 * We are given two strings `a` and `b`.
 * Task: Find the minimum number of times we should repeat string `a` so that `b` is a substring of it.
 * If impossible, return -1.
 *
 * Example:
 *   a = "abcd", b = "cdabcdab"
 *   Repeat "abcd" → "abcdabcdabcd" contains "cdabcdab"
 *   Answer = 3
 *
 * ------------------------------------------------
 * ✅ Brute Force Approach (Not Implemented, Explanation Only):
 * ------------------------------------------------
 * - Keep concatenating `a` to itself until the length >= b.length().
 * - Each time, check if b is a substring using contains().
 * - Also check one more repetition (because b may cross boundary of last repetition).
 * - If found, return count, else return -1.
 * 
 * Time Complexity: O(N * M)   (N = len(a), M = len(b))
 * - Substring search in each repetition is costly.
 *
 * ------------------------------------------------
 * ✅ Optimized Approach (Rabin-Karp for substring search):
 * ------------------------------------------------
 * Intuition:
 * - Instead of repeatedly scanning large strings using contains(), 
 *   use Rabin-Karp hashing to efficiently check if b is substring of repeated a.
 *
 * Steps:
 *   1. Keep repeating `a` until the length of repeated string >= b.length().
 *   2. Use Rabin-Karp substring search to check if b exists.
 *   3. If not found, append one more `a` (to cover cases where b crosses boundary).
 *   4. If still not found → return -1.
 *
 * ------------------------------------------------
 * ✅ Time Complexity:
 * - Let N = length of a, M = length of b.
 * - Building repeated string: O(M + N)
 * - Rabin-Karp search: O(M + N)
 * - Overall: O(M + N)
 *
 * ✅ Space Complexity:
 * - O(M + N) for storing repeated string.
 *
 * ------------------------------------------------
 * ✅ Dry Run Example:
 * ------------------------------------------------
 * Input: a = "abcd", b = "cdabcdab"
 *
 * Step 1: temp = "abcd" (len=4, < b=8) → append → "abcdabcd" (cnt=2)
 * Step 2: Check "abcdabcd" for "cdabcdab" → not found
 * Step 3: Append again → "abcdabcdabcd" (cnt=3)
 * Step 4: Check → "cdabcdab" found at index 2
 * Output = 3
 */

 public class RepeatedStringMatch {

    static int MOD = (int) 1e9 + 7;

    // Compute rolling hash of a string
    public long hashValue(String str, long RADIX, int m) {
        long ans = 0, factor = 1;
        for (int i = m - 1; i >= 0; i--) {
            ans = (ans + ((str.charAt(i) - 'a') * factor) % MOD) % MOD;
            factor = (factor * RADIX) % MOD;
        }
        return ans;
    }

    // Rabin-Karp substring search
    public int rabinKarp(String str, String pattern) {
        int n = str.length(), m = pattern.length();
        if (n < m) return -1;

        long RADIX = 26, MAX_WEIGHT = 1;
        for (int i = 1; i <= m; i++) {
            MAX_WEIGHT = (MAX_WEIGHT * RADIX) % MOD;
        }

        long patHash = hashValue(pattern, RADIX, m), strHash = 0;

        for (int i = 0; i <= n - m; i++) {
            if (i == 0) {
                strHash = hashValue(str, RADIX, m);
            } else {
                strHash = ((strHash * RADIX) % MOD
                         - ((str.charAt(i - 1) - 'a') * MAX_WEIGHT) % MOD
                         + (str.charAt(i + m - 1) - 'a') + MOD) % MOD;
            }

            if (strHash == patHash) {
                // Verify characters (avoid false positives due to hash collision)
                for (int j = 0; j < m; j++) {
                    if (pattern.charAt(j) != str.charAt(j + i)) break;
                    if (j == m - 1) return i; // full match
                }
            }
        }
        return -1;
    }

    // Main function to calculate minimum repetitions
    public int repeatedStringMatch(String a, String b) {
        StringBuilder temp = new StringBuilder();
        temp.append(a);
        int cnt = 1;

        // Keep appending until temp is at least as long as b
        while (temp.length() < b.length()) {
            temp.append(a);
            cnt++;
        }

        // Case 1: Already contains b
        if (rabinKarp(temp.toString(), b) != -1) return cnt;

        // Case 2: Try one more repetition
        temp.append(a);
        cnt++;
        if (rabinKarp(temp.toString(), b) != -1) return cnt;

        // Case 3: Not possible
        return -1;
    }

    // ----------------------------- Main Method (Testing) -----------------------------
    public static void main(String[] args) {
        RepeatedStringMatch solver = new RepeatedStringMatch();

        // Test Case 1
        String a1 = "abcd", b1 = "cdabcdab";
        System.out.println("Test 1: " + solver.repeatedStringMatch(a1, b1)); // Expected 3

        // Test Case 2
        String a2 = "a", b2 = "aa";
        System.out.println("Test 2: " + solver.repeatedStringMatch(a2, b2)); // Expected 2

        // Test Case 3
        String a3 = "abc", b3 = "wxyz";
        System.out.println("Test 3: " + solver.repeatedStringMatch(a3, b3)); // Expected -1

        // Test Case 4
        String a4 = "abc", b4 = "cabcabca";
        System.out.println("Test 4: " + solver.repeatedStringMatch(a4, b4)); // Expected 4
    }
}
