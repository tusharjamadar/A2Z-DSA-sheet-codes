package Basic;
public class StringtoInteger {

    /*
    ✅ Problem: String to Integer (atoi)

    ✅ Problem Details:
    - Parse a string to return a valid 32-bit signed integer according to these steps:
      1. Trim leading whitespaces.
      2. Handle optional sign ('+' or '-').
      3. Read in the number until non-digit is encountered.
      4. Clamp the result within [-2^31, 2^31 - 1].

    ✅ Constraints:
    - Return 0 if no digits found.
    - Clamp if out of 32-bit integer range.

    ✅ Time Complexity:
    - O(n) where n is length of the string.

    ✅ Space Complexity:
    - O(1) for iterative, O(n) for recursive due to call stack.
    */

    // Recursive implementation
    public static int helper(String s, int i, long res, int sign) {
        int n = s.length();
        if (i >= n) return (int) res;

        char c = s.charAt(i);

        if (i == 0 && (c == '-' || c == '+')) {
            sign = (c == '-') ? -1 : 1;
            return helper(s, i + 1, res, sign);
        }

        if (c < '0' || c > '9') return (int) res;

        res = res * 10 + (c - '0') * sign;

        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return helper(s, i + 1, res, sign);
    }

    public int myAtoiRecursive(String s) {
        return helper(s.trim(), 0, 0, 1);
    }

    // Iterative implementation
    public int myAtoiIterative(String s) {
        if (s.equals("")) return 0;

        int n = s.length();
        long res = 0;
        int i = 0;
        int sign = 1;

        while (i < n && s.charAt(i) == ' ') i++;

        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = (s.charAt(i) - '0');
            res = res * 10 + digit;

            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int) res * sign;
    }

    // Main method to test both versions
    public static void main(String[] args) {
        StringtoInteger sol = new StringtoInteger();

        String[] testCases = {
            "42",
            "   -42",
            "4193 with words",
            "words and 987",
            "-91283472332",
            "+1",
            "00000-42a1234",
            "   +0 123"
        };

        System.out.println("Testing Recursive Version:");
        for (String s : testCases) {
            System.out.println("Input: \"" + s + "\" => Output: " + sol.myAtoiRecursive(s));
        }

        System.out.println("\nTesting Iterative Version:");
        for (String s : testCases) {
            System.out.println("Input: \"" + s + "\" => Output: " + sol.myAtoiIterative(s));
        }
    }
}
