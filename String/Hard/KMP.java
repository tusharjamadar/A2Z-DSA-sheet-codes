package String.Hard;

import java.util.ArrayList;

class KMP {
    /*
     * ======================================
     * LONGEST PREFIX SUFFIX (LPS) ARRAY
     * ======================================
     * 
     * Purpose:
     * For each index i in string s, lps[i] stores the length of the longest 
     * proper prefix of s[0..i] which is also a suffix of s[0..i].
     * 
     * Example:
     *   s = "abacaba"
     *   lps = [0,0,1,0,1,2,3]
     * 
     * Explanation:
     *   lps[6] = 3 → "aba" is both prefix and suffix
     * 
     * Why do we need LPS?
     * - When a mismatch happens during pattern matching,
     *   instead of starting over, we can skip ahead using the LPS value.
     * - This makes the search O(n) instead of O(n*m).
     * 
     * Time Complexity: O(n) 
     *   (We only traverse the string once, each character handled at most twice)
     * Space Complexity: O(n) for lps array
     */
    static int[] longestPrefixSuffix(String s) {
        int n = s.length();
        int[] lps = new int[n]; // lps[0] = 0 always, as single char has no prefix=suffix

        for (int i = 1; i < n; i++) {
            int prev_idx = lps[i - 1]; // length of longest prefix-suffix for substring ending at i-1

            // If mismatch occurs, reduce prev_idx to the lps of previous prefix
            while (prev_idx > 0 && s.charAt(i) != s.charAt(prev_idx)) {
                prev_idx = lps[prev_idx - 1]; 
            }

            // If match found, extend prefix-suffix length
            lps[i] = prev_idx + (s.charAt(i) == s.charAt(prev_idx) ? 1 : 0);
        }

        return lps;
    }

    /*
     * ======================================
     * KMP SEARCH FUNCTION
     * ======================================
     * Uses LPS array to find all occurrences of "pat" in "txt".
     * 
     * Steps:
     * 1. Concatenate string: s = pat + '$' + txt
     *    - '$' is a delimiter (not in input alphabet).
     *    - This allows us to compute lps for the combined string and
     *      detect exact matches of pat in txt.
     * 
     * 2. Compute lps array for s.
     * 
     * 3. Traverse lps array:
     *    - If lps[i] == pat.length, it means pattern ended at index i in s.
     * 
     * 4. Translate index i back to text:
     *    - Position in txt = i - 2*m 
     *      (because before txt starts, we had "pat + '$'" of length m+1,
     *       and lps[i] already includes m, so we subtract another m).
     */
    ArrayList<Integer> search(String pat, String txt) {
        // Step 1: Combine pattern, delimiter, and text
        String s = pat + '$' + txt;

        // Step 2: Get LPS array of combined string
        int[] lps = longestPrefixSuffix(s);
        ArrayList<Integer> pos = new ArrayList<>();
        int m = pat.length();

        // Step 3: Traverse lps array from index after "pat + '$'"
        for (int i = m + 1; i < lps.length; i++) {
            if (lps[i] == m) {
                // Step 4: Compute correct index in txt
                pos.add(i - 2 * m);
            }
        }

        return pos;
    }

    // =====================
    // MAIN METHOD for TEST
    // =====================
    public static void main(String[] args) {
        KMP sol = new KMP();

        String txt1 = "abcab", pat1 = "ab";
        System.out.println(sol.search(pat1, txt1)); // [0, 3]

        String txt2 = "abesdu", pat2 = "edu";
        System.out.println(sol.search(pat2, txt2)); // []

        String txt3 = "aabaacaadaabaaba", pat3 = "aaba";
        System.out.println(sol.search(pat3, txt3)); // [0, 9, 12]
    }
}
