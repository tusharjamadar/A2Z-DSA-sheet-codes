
package DynamicProgramming.DpOnStrings;
/*
Problem: 1092. Shortest Common Supersequence (Hard)

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If there are multiple valid strings, return any of them.

Definition:
- A string s is a subsequence of string t if deleting some characters from t (possibly 0) results in s.

---------------------------------------------------
Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
- "abac" is subsequence of "cabac" (remove first 'c')
- "cab" is subsequence of "cabac" (remove last 'ac')
- "cabac" is the shortest valid supersequence.

Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"

---------------------------------------------------
Constraints:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
---------------------------------------------------

INTUITION:
- The Shortest Common Supersequence (SCS) problem is related to the Longest Common Subsequence (LCS).
- If we know the LCS of two strings, then SCS can be built by merging both strings while reusing the common subsequence only once.

Example: str1 = "abac", str2 = "cab"
- LCS = "ab"
- SCS = merge around LCS = "cabac"

WHY LCS helps?
- LCS gives the largest overlapping subsequence.
- If we merge around LCS, we avoid repeating characters unnecessarily.

---------------------------------------------------
BRUTE FORCE (Exponential, not feasible for large n):
- Generate all supersequences of str1 and str2.
- Pick the shortest one containing both.
- Time Complexity: O(2^(n+m)) → impossible for n, m up to 1000.

---------------------------------------------------
OPTIMIZED APPROACH (DP + Reconstruction):
1. Compute LCS length using DP table of size (n+1)*(m+1).
   dp[i][j] = length of LCS of str1[0..i-1], str2[0..j-1]

2. Reconstruct the SCS using the dp table:
   - Start from dp[n][m] and move backwards:
     - If characters match → take that char in answer.
     - If not match → move in direction of larger dp value.
   - Append remaining characters.

3. Reverse the result (since we built it backwards).

---------------------------------------------------
DRY RUN:
str1 = "abac", str2 = "cab"

Step 1: Build LCS table
dp[n][m] after filling → LCS = "ab"

Step 2: Reconstruct SCS
Traverse from (4,3):
- Compare chars, move accordingly → final result = "cabac"

---------------------------------------------------
TIME COMPLEXITY:
- Building DP: O(n*m)
- Reconstruction: O(n+m)
- Total: O(n*m)

SPACE COMPLEXITY:
- O(n*m) for DP table
(Can be optimized to O(min(n,m)) if only LCS length needed, but we need full table for reconstruction)

---------------------------------------------------
*/

public class ShortestCommonSupersequence {

    // Function to compute Shortest Common Supersequence
    public String shortestCommonSuperseq(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n+1][m+1];

        // Step 1: Compute LCS using DP
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Step 2: Reconstruct the Shortest Common Supersequence
        int i = n, j = m;
        StringBuilder ans = new StringBuilder();

        while(i > 0 && j > 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                // Same character → include only once
                ans.append(str1.charAt(i-1));
                i--;
                j--;
            } else if(dp[i-1][j] > dp[i][j-1]){
                // Move in direction of larger LCS
                ans.append(str1.charAt(i-1));
                i--;
            } else {
                ans.append(str2.charAt(j-1));
                j--;
            }
        }

        // Add remaining characters (if any)
        while(i > 0){
            ans.append(str1.charAt(i-1));
            i--;
        }
        while(j > 0){
            ans.append(str2.charAt(j-1));
            j--;
        }

        // Reverse since we built the string backwards
        return ans.reverse().toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        ShortestCommonSupersequence sol = new ShortestCommonSupersequence();

        // Test Case 1
        String str1 = "abac", str2 = "cab";
        System.out.println("Input: str1 = " + str1 + ", str2 = " + str2);
        System.out.println("Output: " + sol.shortestCommonSuperseq(str1, str2));
        System.out.println("Expected: cabac\n");

        // Test Case 2
        str1 = "aaaaaaaa";
        str2 = "aaaaaaaa";
        System.out.println("Input: str1 = " + str1 + ", str2 = " + str2);
        System.out.println("Output: " + sol.shortestCommonSuperseq(str1, str2));
        System.out.println("Expected: aaaaaaaa\n");

        // Test Case 3
        str1 = "geek";
        str2 = "eke";
        System.out.println("Input: str1 = " + str1 + ", str2 = " + str2);
        System.out.println("Output: " + sol.shortestCommonSuperseq(str1, str2));
        // Expected: "geeke" or "gekek" (both valid shortest supersequences)
    }
}
