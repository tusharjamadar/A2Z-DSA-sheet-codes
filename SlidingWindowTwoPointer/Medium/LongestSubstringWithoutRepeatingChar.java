package SlidingWindowTwoPointer.Medium;

/*
 * ✅ Problem:
 * Given a string `s`, find the length of the longest substring without repeating characters.
 *
 * 💡 Intuition:
 * - Use a sliding window to maintain the current non-repeating substring.
 * - Track the last seen index of each character and move the left pointer if a duplicate is found within the window.
 *
 * 🧠 Algorithm:
 * 1. Use an array `hash` of size 256 (ASCII) initialized to -1.
 * 2. Traverse the string with two pointers: left (`l`) and right (`r`).
 * 3. If the character at `r` was seen in the current window (i.e., hash value ≥ `l`), update `l` to one position right of that.
 * 4. Update `hash[s[r]]` to the current index.
 * 5. Update the result with max window size seen so far.
 *
 * ⏱️ Time Complexity: O(n)
 * 🗃️ Space Complexity: O(1) — fixed size array of 256
 *
 * 🔁 Dry Run (s = "abcabcbb"):
 *
 *  i = 0: char = 'a' → hash[a] = -1 → res = 1, update hash[a] = 0
 *  i = 1: char = 'b' → hash[b] = -1 → res = 2, update hash[b] = 1
 *  i = 2: char = 'c' → hash[c] = -1 → res = 3, update hash[c] = 2
 *  i = 3: char = 'a' → hash[a] = 0 → a is in window → l = 1 → res = 3
 *  i = 4: char = 'b' → hash[b] = 1 → b is in window → l = 2 → res = 3
 *  i = 5: char = 'c' → hash[c] = 2 → c is in window → l = 3 → res = 3
 *  i = 6: char = 'b' → hash[b] = 4 → b is in window → l = 5 → res = 3
 *  i = 7: char = 'b' → hash[b] = 6 → b is in window → l = 7 → res = 3
 *
 * Final Answer: 3 ("abc")
 */

class LongestSubstringWithoutRepeatingChar {
    public int lengthOfLongestSubstring(String s) {
        int[] hash = new int[256];
        for (int i = 0; i < 256; i++) hash[i] = -1;

        int n = s.length(), l = 0, r = 0, res = 0;

        while (r < n) {
            char currChar = s.charAt(r);

            if (hash[currChar] != -1 && hash[currChar] >= l) {
                l = hash[currChar] + 1;
            }

            res = Math.max(res, r - l + 1);
            hash[currChar] = r;
            r++;
        }

        return res;
    }

    // ✅ Main function to test the implementation
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingChar sol = new LongestSubstringWithoutRepeatingChar();

        String input1 = "abcabcbb";
        String input2 = "bbbbb";
        String input3 = "pwwkew";
        String input4 = "";
        String input5 = "dvdf";

        System.out.println("Input: " + input1 + " → Output: " + sol.lengthOfLongestSubstring(input1)); // 3
        System.out.println("Input: " + input2 + " → Output: " + sol.lengthOfLongestSubstring(input2)); // 1
        System.out.println("Input: " + input3 + " → Output: " + sol.lengthOfLongestSubstring(input3)); // 3
        System.out.println("Input: " + input4 + " → Output: " + sol.lengthOfLongestSubstring(input4)); // 0
        System.out.println("Input: " + input5 + " → Output: " + sol.lengthOfLongestSubstring(input5)); // 3
    }
}
