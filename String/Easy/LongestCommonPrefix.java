package String.Easy;

/*
Problem: 14. Longest Common Prefix

Problem Details:
Write a function to find the longest common prefix string among an array of strings.
If there is no common prefix, return an empty string "".

Examples:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: No common prefix exists.

Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] contains only lowercase English letters.

Intuition:
- If we sort the array of strings, the first and last strings will have the minimum and maximum lexicographical difference.
- So, comparing only these two strings gives the common prefix across the entire array.

Algorithm:
1. Sort the array lexicographically.
2. Compare characters of the first and last string until mismatch.
3. Return the common prefix formed from matched characters.

Time Complexity:
- O(N * log N) for sorting, where N is the number of strings.
- O(M) for comparing characters, where M is the length of the shortest string.
- Overall: O(N log N + M)

Space Complexity:
- O(1) extra space (ignoring output and sorting)
*/

import java.util.*;

class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();

        // Step 1: Sort the array of strings
        Arrays.sort(strs);

        // Step 2: Compare first and last string in sorted array
        String first = strs[0];
        String last = strs[strs.length - 1];

        int minLen = Math.min(first.length(), last.length());

        for (int i = 0; i < minLen; i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
            ans.append(first.charAt(i));
        }

        return ans.toString();
    }

    // Main method to test the function
    public static void main(String[] args) {
        LongestCommonPrefix sol = new LongestCommonPrefix();

        String[] input1 = {"flower", "flow", "flight"};
        String[] input2 = {"dog", "racecar", "car"};
        String[] input3 = {"interspecies", "interstellar", "interstate"};

        System.out.println("Output 1: " + sol.longestCommonPrefix(input1)); // Expected: "fl"
        System.out.println("Output 2: " + sol.longestCommonPrefix(input2)); // Expected: ""
        System.out.println("Output 3: " + sol.longestCommonPrefix(input3)); // Expected: "inters"
    }
}

