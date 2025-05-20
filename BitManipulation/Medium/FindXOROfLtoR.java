package BitManipulation.Medium;
/*
Problem Description:
You are given two integers L and R, your task is to find the XOR of all integers from L to R (inclusive).
Example:
Input: L = 4, R = 8
Output: 8
Explanation: 4 ^ 5 ^ 6 ^ 7 ^ 8 = 8

Intuition:
The XOR of all numbers from 1 to N follows a pattern based on N % 4:
- If N % 4 == 0 => XOR(1 to N) = N
- If N % 4 == 1 => XOR(1 to N) = 1
- If N % 4 == 2 => XOR(1 to N) = N + 1
- If N % 4 == 3 => XOR(1 to N) = 0

To get XOR from L to R:
- XOR(L to R) = XOR(1 to R) ^ XOR(1 to L-1)

Algorithm:
1. Define a function getXor(n) to compute XOR of all numbers from 1 to n using the above rules.
2. Compute XOR(L to R) as getXor(R) ^ getXor(L - 1)

Time Complexity: O(1)
- We are using a constant time formula to compute the result.

Space Complexity: O(1)
- No extra space is used.
*/

class FindXOROfLtoR {

    // Helper function to compute XOR from 1 to n
    public static int getXor(int n){
        // Pattern of XOR from 1 to n based on n % 4
        if(n % 4 == 0) return n;       // Case 0: XOR is n
        else if(n % 4 == 1) return 1;  // Case 1: XOR is 1
        else if(n % 4 == 2) return n + 1; // Case 2: XOR is n+1
        else return 0;                 // Case 3: XOR is 0
    }

    // Main function to compute XOR from l to r
    public static int findXOR(int l, int r) {
        // XOR(L to R) = XOR(1 to R) ^ XOR(1 to L-1)
        return getXor(l - 1) ^ getXor(r);
    }

    // Main function to test the code
    public static void main(String[] args) {
        int L = 4, R = 8;
        System.out.println("XOR from " + L + " to " + R + " = " + findXOR(L, R)); // Expected output: 8

        L = 1; R = 5;
        System.out.println("XOR from " + L + " to " + R + " = " + findXOR(L, R)); // Expected output: 1

        L = 10; R = 15;
        System.out.println("XOR from " + L + " to " + R + " = " + findXOR(L, R)); // Expected output: 5
    }
}
