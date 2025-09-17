package String.Hard;

class ShortestPalindrome {
    /*
     * ======================================================
     * Function: longestPrefixSufix (KMP's LPS Array Builder)
     * ======================================================
     * 
     * Purpose:
     * Build the LPS (Longest Prefix Suffix) array for a given string.
     * 
     * lps[i] = the length of the longest prefix of s[0..i] 
     *          which is also a suffix of s[0..i].
     * 
     * Why needed here?
     * - In this problem, we want the longest prefix of the original string `s`
     *   that matches a suffix of the reversed string `rev`.
     * - This tells us how much of the prefix is already a palindrome.
     * - The leftover part of `rev` (not matching) is what we need to add in front.
     * 
     * Example: s = "aacecaaa"
     * rev = "aaacecaa"
     * combined = "aacecaaa#aaacecaa"
     * 
     * LPS helps us find longest palindromic prefix = "aacecaa"
     */
    private int[] longestPrefixSufix(String s) {
        int n = s.length();
        int[] lps = new int[n];  // lps[0] = 0 always

        for (int i = 1; i < n; i++) {
            int prev_idx = lps[i - 1];

            // If mismatch → shrink prev_idx using previous lps
            while (prev_idx > 0 && s.charAt(i) != s.charAt(prev_idx)) {
                prev_idx = lps[prev_idx - 1];
            }

            // If characters match, extend the prefix
            lps[i] = prev_idx + (s.charAt(i) == s.charAt(prev_idx) ? 1 : 0);
        }

        return lps;
    }

    /*
     * Utility: Reverse a string using StringBuilder
     */
    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }

    /*
     * ======================================================
     * Main Function: shortestPalindrome
     * ======================================================
     * Intuition:
     * - We want the shortest palindrome that starts at index 0.
     * - Idea: Mirror the string and see how much overlap exists between
     *   original string `s` and reversed string `rev`.
     * 
     * Steps:
     * 1. Reverse string → rev
     * 2. Create combined string: s + "#" + rev
     *    - '#' is just a separator to avoid overlap ambiguity.
     * 3. Build LPS for combined.
     * 4. lps[lastIndex] gives us the length of the longest prefix of s
     *    which is also a suffix of rev → longest palindromic prefix.
     * 5. The part of rev that is not included in this palindrome prefix
     *    must be added in front.
     * 
     * Return: rev.substring(0, n - lps[2*n]) + s
     * 
     * Example 1:
     * s = "aacecaaa"
     * rev = "aaacecaa"
     * combined = "aacecaaa#aaacecaa"
     * lps[2*n] = 7 → palindrome prefix = "aacecaa"
     * Add "a" from rev → "aaacecaaa"
     * 
     * Example 2:
     * s = "abcd"
     * rev = "dcba"
     * combined = "abcd#dcba"
     * lps[2*n] = 0 → no palindrome prefix
     * Add full rev except overlap = "dcbabcd"
     */
    public String shortestPalindrome(String s) {
        int n = s.length();
        String rev = reverse(s);

        // Step 2: Build combined string
        String combined = s + "#" + rev;

        // Step 3: Build LPS
        int[] lps = longestPrefixSufix(combined);

        // Step 4: lps[2*n] gives longest palindrome prefix length
        return (rev.substring(0, n - lps[2 * n]) + s);
    }

    // ==============================
    // Main Method for Testing
    // ==============================
    public static void main(String[] args) {
        ShortestPalindrome sol = new ShortestPalindrome();

        // Example 1
        String s1 = "aacecaaa";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + sol.shortestPalindrome(s1));
        // Expected: "aaacecaaa"

        // Example 2
        String s2 = "abcd";
        System.out.println("Input: " + s2);
        System.out.println("Output: " + sol.shortestPalindrome(s2));
        // Expected: "dcbabcd"

        // Extra Example
        String s3 = "race";
        System.out.println("Input: " + s3);
        System.out.println("Output: " + sol.shortestPalindrome(s3));
        // Expected: "ecarace"
    }
}
