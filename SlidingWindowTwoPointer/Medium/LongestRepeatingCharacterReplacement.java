package SlidingWindowTwoPointer.Medium;
/*
424. Longest Repeating Character Replacement

🧩 Problem:
Given a string `s` and an integer `k`, you can replace at most `k` characters in the string to make the longest possible substring where all characters are the same. Return the length of such a substring.

🧠 Intuition:
We want to find the **longest substring** in which we can convert at most `k` characters to make the entire substring consist of the same character.

The key observation is: in any window, if the number of characters we need to change = (window size - max frequency character count), 
and that is <= k, then the window is valid.

🧪 Dry Run:
s = "AABABBA", k = 1
Window: "AABA"
Frequencies: A=3, B=1 → window size = 4, maxFreq = 3 → changes = 1 → valid
Window: "ABABB" → A=2, B=3 → window size = 5, maxFreq = 3 → changes = 2 → invalid → shrink window from left

✅ Algorithm (Sliding Window + Hash Array):
1. Use two pointers `left` and `right` to maintain a sliding window.
2. Track character frequencies in the window using a hash array of size 26.
3. Track the frequency of the most common character `maxFreq`.
4. While (window size - maxFreq) > k → shrink window from left.
5. At each step, update the max length.

⏱ Time Complexity: O(n)
Only one pass through the string. Each character is visited at most twice.

📦 Space Complexity: O(26) → O(1) constant space
*/

class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, maxLen = 0, maxFreq = 0;
        int n = s.length();

        int[] hash = new int[26]; // Stores frequency of characters in window

        while (right < n) {
            char ch = s.charAt(right);
            hash[ch - 'A']++;
            maxFreq = Math.max(maxFreq, hash[ch - 'A']);

            // If number of replacements needed > k, shrink window
            if ((right - left + 1) - maxFreq > k) {
                hash[s.charAt(left) - 'A']--;
                left++;
            }

            // Update max length of valid window
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    // 🔍 Main method to test the solution
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement sol = new LongestRepeatingCharacterReplacement();

        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Output 1: " + sol.characterReplacement(s1, k1)); // Expected: 4

        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("Output 2: " + sol.characterReplacement(s2, k2)); // Expected: 4

        String s3 = "AAAA";
        int k3 = 2;
        System.out.println("Output 3: " + sol.characterReplacement(s3, k3)); // Expected: 4

        String s4 = "ABCDE";
        int k4 = 1;
        System.out.println("Output 4: " + sol.characterReplacement(s4, k4)); // Expected: 2
    }
}
