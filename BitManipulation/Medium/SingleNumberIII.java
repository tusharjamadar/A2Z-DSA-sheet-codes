package BitManipulation.Medium;

/*
Problem:
Given an array of integers `nums`, where:
- Exactly two elements appear only once.
- All other elements appear exactly twice.

Return the two elements that appear only once.
Constraints:
- Time: O(n)
- Space: O(1)

Example:
Input:  [1,2,1,3,2,5]
Output: [3,5] or [5,3]
*/

class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers. Result is XOR of the two unique numbers (a ^ b)
        int xor = 0;
        for (int val : nums) {
            xor ^= val;
        }

        // Step 2: Find any set bit in xor (i.e., a bit where a and b differ)
        // This finds the rightmost set bit
        int mask = xor & -xor;

        // Step 3: Divide numbers into two groups based on the set bit
        int a = 0, b = 0;
        for (int val : nums) {
            if ((val & mask) == 0) {
                a ^= val;
            } else {
                b ^= val;
            }
        }

        return new int[]{a, b};
    }

    // Test the solution
    public static void main(String[] args) {
        SingleNumberIII sol = new SingleNumberIII();

        int[] result1 = sol.singleNumber(new int[]{1, 2, 1, 3, 2, 5});
        System.out.println("Unique numbers: " + result1[0] + ", " + result1[1]); // Output: [3,5]

        int[] result2 = sol.singleNumber(new int[]{-1, 0});
        System.out.println("Unique numbers: " + result2[0] + ", " + result2[1]); // Output: [-1,0]

        int[] result3 = sol.singleNumber(new int[]{0, 1});
        System.out.println("Unique numbers: " + result3[0] + ", " + result3[1]); // Output: [0,1]
    }
}

/*
Intuition:
- If every element appears twice except for two, then XORing all will cancel out duplicates:
    e.g., a ^ a = 0, so all duplicates cancel.
- Let a and b be the two unique numbers => xor = a ^ b
- In xor, at least one bit is 1 (since a != b). That bit differs between a and b.
- We can split the array into two groups using this differing bit and XOR each group separately.

Bitwise Trick:
- xor & -xor gives the rightmost set bit.
- Or you can use: xor ^ (xor & (xor - 1)) to get the lowest differing bit as mask.
*/

