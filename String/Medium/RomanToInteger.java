package String.Medium;

/*
Problem: 13. Roman to Integer

Problem Details:
Convert a Roman numeral string to an integer using the values:
I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
Special subtraction rules:
- I before V (5) or X (10) → 4, 9
- X before L (50) or C (100) → 40, 90
- C before D (500) or M (1000) → 400, 900

Examples:
Input: "III"      → Output: 3
Input: "LVIII"    → Output: 58 (L + V + III)
Input: "MCMXCIV"  → Output: 1994 (M + CM + XC + IV)

Intuition:
- If a smaller value is followed by a larger one (like I before V), subtract it.
- Else, add the value.

Algorithm:
1. Traverse the Roman string from left to right.
2. If the current character's value is less than the next, subtract it.
3. Otherwise, add it to the result.
4. Add the last character's value separately to initialize the result.

Time Complexity: O(n), where n is the length of the string  
Space Complexity: O(1), constant extra space
*/

class RomanToInteger {

    // Convert Roman character to integer value
    public int romanCharToNum(char ch){
        switch(ch){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0;
        }
    }

    public int romanToInt(String s) {
        int n = s.length();
        int res = romanCharToNum(s.charAt(n - 1)); // Start with last character

        for (int i = 0; i < n - 1; i++) {
            int curr = romanCharToNum(s.charAt(i));
            int next = romanCharToNum(s.charAt(i + 1));

            if (curr < next) {
                res -= curr; // Subtract if current is less than next
            } else {
                res += curr; // Otherwise, add
            }
        }

        return res;
    }

    // Main method to test the function
    public static void main(String[] args) {
        RomanToInteger sol = new RomanToInteger();
        System.out.println(sol.romanToInt("III"));      // 3
        System.out.println(sol.romanToInt("LVIII"));    // 58
        System.out.println(sol.romanToInt("MCMXCIV"));  // 1994
    }
}
