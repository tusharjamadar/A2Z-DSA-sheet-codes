package SubsequencesPattern;
import java.util.*;

/*
Problem: 17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations 
that the number could represent using the classic phone keypad mapping.

Example:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Constraints:
- 0 <= digits.length <= 4
- digits[i] is a digit in the range ['2', '9']

Phone digit to letter mapping:
2 -> abc
3 -> def
4 -> ghi
5 -> jkl
6 -> mno
7 -> pqrs
8 -> tuv
9 -> wxyz

Intuition:
Each digit maps to a set of characters. We can think of this as a tree where each level corresponds 
to a digit, and each branch corresponds to a character choice for that digit. So we use backtracking 
to build all possible paths (i.e., combinations of letters).

Algorithm:
1. Create a helper function to map digits to letters.
2. Use a recursive function (backtracking) to explore all character combinations.
3. At each recursion level, loop through characters mapped from current digit and append to a builder.
4. When we reach the end of the digits string, add the built string to the result list.
5. Use backtracking by removing the last added character before going to the next branch.

Time Complexity:
- O(4^n): In the worst case (e.g., all digits are '7' or '9' with 4 letters each), there are 4^n combinations.
- n = digits.length (max 4, so manageable)

Space Complexity:
- O(n) recursion stack space
*/

class KeypadLetterCombinations {

    // Helper function to get corresponding letters for a digit
    private static String getLetters(char ch) {
        switch (ch) {
            case '2': return "abc";
            case '3': return "def";
            case '4': return "ghi";
            case '5': return "jkl";
            case '6': return "mno";
            case '7': return "pqrs";
            case '8': return "tuv";
            case '9': return "wxyz";
            default: return ""; // for invalid digits like '1'
        }
    }

    // Main function to initiate letter combinations
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        // Edge case: if input is empty, return empty result
        if (digits.length() == 0) return res;

        // Use a StringBuilder to build combinations
        StringBuilder sb = new StringBuilder();
        solve(digits, 0, sb, res);
        return res;
    }

    // Recursive backtracking function
    private static void solve(String digits, int start, StringBuilder temp, List<String> res) {
        // Base case: when we've built a full combination
        if (start == digits.length()) {
            res.add(temp.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        char ch = digits.charAt(start);
        for (char letter : getLetters(ch).toCharArray()) {
            // Choose
            temp.append(letter);

            // Explore
            solve(digits, start + 1, temp, res);

            // Un-choose (backtrack)
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    // Test the solution with a main method
    public static void main(String[] args) {
        KeypadLetterCombinations sol = new KeypadLetterCombinations();

        System.out.println("Combinations for '23': " + sol.letterCombinations("23"));
        System.out.println("Combinations for '2': " + sol.letterCombinations("2"));
        System.out.println("Combinations for '': " + sol.letterCombinations(""));
    }
}
