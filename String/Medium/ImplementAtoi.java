package String.Medium;

class ImplementAtoi {

    /*
    ✅ Problem: 8. String to Integer (atoi)
    ✅ Problem Details:
    Convert a given string to a 32-bit signed integer (int in Java) following these steps:
    - Ignore leading whitespaces.
    - Detect optional sign (+ or -).
    - Read numeric digits until a non-digit is encountered.
    - Clamp the result to [-2^31, 2^31 - 1] if it exceeds bounds.

    ✅ Intuition:
    We scan the string from left to right:
    - Skip whitespaces first.
    - Handle an optional sign.
    - Convert valid digits to a number, ensuring we don't overflow.
    - Stop when we hit an invalid character.

    ✅ Algorithm:
    1. Skip leading spaces.
    2. Detect sign.
    3. Process digits to build the number.
    4. Stop on non-digit or end.
    5. Clamp to Integer.MIN_VALUE or Integer.MAX_VALUE if out of range.

    ✅ Time & Space Complexity:
    - Time: O(n), where n is the length of the input string.
    - Space: O(1), no extra space used.
    */

    public int myAtoi(String s) {
        if (s.equals("")) return 0;

        int n = s.length();
        long res = 0;
        int i = 0;
        int sign = 1;

        // Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') i++;

        // Check for optional '+' or '-' sign
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Process digits and build the number
        while (i < n && (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
            int digit = (s.charAt(i) - '0') * sign;
            res = res * 10 + digit;

            // Clamp result if it goes out of 32-bit signed int range
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int) res;
    }
    // Main method to test various cases
    public static void main(String[] args) {
        ImplementAtoi sol = new ImplementAtoi();

        System.out.println(sol.myAtoi("42"));         // 42
        System.out.println(sol.myAtoi("   -042"));     // -42
        System.out.println(sol.myAtoi("1337c0d3"));    // 1337
        System.out.println(sol.myAtoi("0-1"));         // 0
        System.out.println(sol.myAtoi("words and 987"));// 0
        System.out.println(sol.myAtoi("-91283472332")); // -2147483648 (clamped)
        System.out.println(sol.myAtoi("91283472332"));  // 2147483647 (clamped)
    }
}

