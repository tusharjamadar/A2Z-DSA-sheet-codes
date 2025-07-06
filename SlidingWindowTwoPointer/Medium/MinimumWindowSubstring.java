package SlidingWindowTwoPointer.Medium;
/**
 * 76. Minimum Window Substring
 * 
 * ✅ Problem Statement:
 * Given two strings s and t, return the minimum window substring of s such that every character in t 
 * (including duplicates) is included in the window. If no such substring exists, return an empty string "".
 * The answer is guaranteed to be unique.
 * 
 * Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 */

class MinimumWindowSubstring {

    /**
     * ✅ Intuition (Brute-force using character frequency check):
     * We use two frequency arrays `need` and `have` to track the character requirements.
     * For every window in string `s`, check if it satisfies the frequency required by `t`.
     * Shrink the window from left whenever it's valid and update the result if it's the smallest valid window.
     *
     * ✅ Algorithm:
     * 1. Count frequency of all characters in string `t` using `need` array.
     * 2. Iterate through `s` with two pointers (sliding window): right and left.
     * 3. Use `have` array to track current window frequency.
     * 4. While the current window is valid, try to shrink from left to find the minimum.
     * 5. Update the answer whenever a smaller valid window is found.
     *
     * ✅ Dry run:
     * s = "ADOBECODEBANC", t = "ABC"
     * need = {A:1, B:1, C:1}, have = {}
     * Valid window found at "BANC" → return it.
     *
     * ✅ Time Complexity: O(256 * n) => O(n) where n = s.length (constant alphabet size = 256)
     * ✅ Space Complexity: O(256)
     */
    public static String minWindowBruteForce(String s, String t) {
        int[] need = new int[256];
        int[] have = new int[256];

        for (char ch : t.toCharArray()) need[ch]++;

        int minLen = Integer.MAX_VALUE;
        int start = -1;
        int n = s.length();

        int left = 0, right = 0;
        while (right < n) {
            have[s.charAt(right)]++;

            while (containsAll(need, have)) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                have[s.charAt(left)]--;
                left++;
            }
            right++;
        }

        return start == -1 ? "" : s.substring(start, start + minLen);
    }

    private static boolean containsAll(int[] need, int[] have) {
        for (int i = 0; i < 256; i++) {
            if (need[i] > have[i]) return false;
        }
        return true;
    }

    /**
     * ✅ Intuition (Optimized with two counters):
     * Instead of checking entire arrays, track how many unique characters from `t` are fully matched in current window.
     * This reduces unnecessary checking.
     *
     * ✅ Algorithm:
     * 1. Count frequency of `t` in `need` array and `required` (number of unique chars).
     * 2. Use sliding window to track frequency in `have` and `formed` (how many chars match the required freq).
     * 3. Expand window from right, and contract from left when `formed == required`.
     * 4. Track the minimum valid window.
     *
     * ✅ Dry Run:
     * s = "ADOBECODEBANC", t = "ABC"
     * need = {A:1, B:1, C:1}, formed = 3 when all characters are matched.
     * Shrink window until it becomes invalid → update min window when formed == required.
     *
     * ✅ Time Complexity: O(n)
     * ✅ Space Complexity: O(256)
     */
    public static String minWindowOptimized(String s, String t) {
        int[] need = new int[256];
        int[] have = new int[256];

        int required = 0, formed = 0;
        for (char ch : t.toCharArray()) {
            if (need[ch] == 0) required++;
            need[ch]++;
        }

        int left = 0, right = 0, start = -1, minLen = Integer.MAX_VALUE;
        int n = s.length();

        while (right < n) {
            char ch = s.charAt(right);
            have[ch]++;
            if (need[ch] != 0 && have[ch] == need[ch]) {
                formed++;
            }

            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char l_ch = s.charAt(left);
                have[l_ch]--;
                if (need[l_ch] != 0 && have[l_ch] < need[l_ch]) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return start == -1 ? "" : s.substring(start, start + minLen);
    }

    /**
     * ✅ Main method to test both implementations
     */
    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";

        System.out.println("Brute Force Result: " + minWindowBruteForce(s1, t1)); // Output: BANC
        System.out.println("Optimized Result:   " + minWindowOptimized(s1, t1));   // Output: BANC

        String s2 = "a", t2 = "a";
        System.out.println("Brute Force Result: " + minWindowBruteForce(s2, t2)); // Output: a
        System.out.println("Optimized Result:   " + minWindowOptimized(s2, t2));   // Output: a

        String s3 = "a", t3 = "aa";
        System.out.println("Brute Force Result: " + minWindowBruteForce(s3, t3)); // Output: ""
        System.out.println("Optimized Result:   " + minWindowOptimized(s3, t3));   // Output: ""
    }
}
