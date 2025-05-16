package Hard;

import java.util.*;

/*
Problem: 131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitionings of s.

Example:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Constraints:
- 1 <= s.length <= 16
- s contains only lowercase English letters.

--------------------------------------------------------------

Intuition:
We need to try every possible partitioning of the string and check if every substring in that partition
is a palindrome. This is a classic backtracking problem where we explore all partitions and keep only
those where every part is a palindrome.

--------------------------------------------------------------

Algorithm:
1. Start backtracking from index 0 of the string.
2. For each position i from 'start' to end of string:
   a. Check if substring s[start...i] is a palindrome.
   b. If yes, add substring to current path and recursively solve from index i+1.
   c. After recursion, backtrack by removing the last added substring.
3. If 'start' reaches the end of the string, add the current path to result as a valid partition.

--------------------------------------------------------------

Time Complexity:
Let n = length of string.
- Each character can either be a cut point or not → 2^(n-1) possible partitions.
- For each substring, checking if it’s a palindrome takes O(n).
- So overall worst-case complexity is O(n * 2^n).

Space Complexity:
- O(n) recursion stack depth and O(n) space for each path.

*/

class PalindromePartitioning {

    // Main function to initiate backtracking
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        solve(s, 0, new ArrayList<>(), res);  // Start from index 0
        return res;
    }

    // Recursive backtracking function
    public void solve(String s, int start, List<String> path, List<List<String>> res) {
        // Base case: if we've reached end of the string, add current path to result
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // Try all possible substrings starting from 'start'
        for (int i = start; i < s.length(); i++) {
            // If substring s[start..i] is a palindrome, we proceed
            if (isPalindrome(s, start, i)) {
                // Choose the current palindrome substring
                path.add(s.substring(start, i + 1));

                // Explore further partitions starting from index i+1
                solve(s, i + 1, path, res);

                // Backtrack: remove the last added substring to try other partitions
                path.remove(path.size() - 1);
            }
        }
    }

    // Helper function to check if s[start..end] is a palindrome
    public boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    // Optional: Test the code with a sample input
    public static void main(String[] args) {
        PalindromePartitioning sol = new PalindromePartitioning();
        System.out.println("Partitions for 'aab': " + sol.partition("aab"));
        System.out.println("Partitions for 'a': " + sol.partition("a"));
    }
}
