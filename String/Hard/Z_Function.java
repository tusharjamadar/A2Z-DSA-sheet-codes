package String.Hard;

import java.util.ArrayList;

class Z_Function {
    /*
     * ===============================
     * Z-FUNCTION (Z-Algorithm Utility)
     * ===============================
     * 
     * Problem Context:
     * We want to find all occurrences of a pattern "pat" inside a text "txt".
     * The Z-algorithm helps us compute the longest common prefix (LCP) 
     * between the string and its suffixes efficiently.
     * 
     * Idea:
     * For a string s, z[i] = length of the longest substring starting at i
     * which is also a prefix of s.
     * 
     * Example:
     * s = "aabxaayaab"
     * z[] = [0,1,0,0,2,1,0,3,2,1]
     * Explanation:
     *   z[1] = 1 because prefix "a" matches "a" at index 1
     *   z[4] = 2 because prefix "aa" matches "aa" at index 4
     *   z[7] = 3 because prefix "aab" matches "aab" at index 7
     * 
     * We use this to search pattern occurrences by joining "pat + '$' + txt"
     * and looking where the z-value equals length of pat.
     * 
     * Time Complexity: O(n) 
     *   where n = length of combined string (pattern + '$' + text)
     * Space Complexity: O(n) for z array
     */

    static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n]; // z[i] stores LCP length of s[i..n-1] with prefix s[0..n-1]

        int l = 0, r = 0; // [l, r] is the window that matches with prefix of s

        for (int i = 1; i < n; i++) { // start from 1 because z[0] = 0 by definition
            if (i <= r) {
                // k = relative index inside the current [l, r] window
                int k = i - l;
                // we can use previously computed z[k] (reusing prefix info)
                // but we cannot exceed r - i + 1
                z[i] = Math.min(r - i + 1, z[k]);
            }

            // Extend the match beyond current [l, r]
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i] = z[i] + 1;
            }

            // If new z-box goes beyond current [l, r], update l and r
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        return z;
    }

    /*
     * ===========================
     * SEARCH FUNCTION
     * ===========================
     * Uses Z-function to find all occurrences of "pat" in "txt".
     * 
     * Steps:
     * 1. Concatenate strings: s = pat + '$' + txt
     *    - Why? This ensures that occurrences of pat in txt will appear
     *      as prefix matches in z-array after position (pat.length + 1).
     *    - '$' acts as a delimiter to avoid overlaps.
     * 
     * 2. Compute z array for s.
     * 
     * 3. If z[i] == pat.length, it means the substring starting at i in s
     *    is exactly equal to the pattern.
     * 
     * 4. Translate index from combined string "s" to actual index in txt:
     *    position = i - (pat.length + 1)
     */

    ArrayList<Integer> search(String pat, String txt) {
        // Step 1: Combine pattern, delimiter, and text
        String s = pat + '$' + txt;

        // Step 2: Get z array of the combined string
        int[] z = zFunction(s);

        ArrayList<Integer> pos = new ArrayList<>();
        int m = pat.length();

        // Step 3: Traverse z array starting after "pat + '$'"
        for (int i = m + 1; i < z.length; i++) {
            // If z[i] == pattern length, means pattern found at index
            if (z[i] == m) {
                // Step 4: Adjust index to match txt (0-based)
                pos.add(i - m - 1);
            }
        }

        return pos;
    }

    // =====================
    // MAIN METHOD for TEST
    // =====================
    public static void main(String[] args) {
        Z_Function sol = new Z_Function();

        String txt1 = "abcab", pat1 = "ab";
        System.out.println(sol.search(pat1, txt1)); // [0, 3]

        String txt2 = "abesdu", pat2 = "edu";
        System.out.println(sol.search(pat2, txt2)); // []

        String txt3 = "aabaacaadaabaaba", pat3 = "aaba";
        System.out.println(sol.search(pat3, txt3)); // [0, 9, 12]
    }
}
