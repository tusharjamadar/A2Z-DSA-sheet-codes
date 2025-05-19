package BitManipulation.Medium;

/*
Problem:
Given an integer array `nums` where every element appears exactly three times except one element which appears only once.
Find that single element and return it.

You must solve it in:
- Linear time (O(n))
- Constant space (O(1))

Example:
Input: [2,2,3,2]
Output: 3

Input: [0,1,0,1,0,1,99]
Output: 99

Intuition:
We want to track bits that have appeared once and twice, using bitwise operations:
- `ones`: stores bits that have appeared exactly once so far.
- `twos`: stores bits that have appeared exactly twice so far.

Key idea:
- For every bit, we update `ones` and `twos` accordingly.
- If a bit appears a third time, it is removed from both `ones` and `twos`.

This effectively cancels out numbers appearing 3 times, and leaves only the unique number.

Algorithm:
1. Initialize `ones = 0`, `twos = 0`.
2. For each number in `nums`:
   - Update `ones` with bits that have appeared once but not in `twos`.
   - Update `twos` with bits that have appeared twice but not in updated `ones`.
3. After the loop, `ones` contains the single number.

Time Complexity: O(n)
Space Complexity: O(1)
*/

class SignleNumberII {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            // Add current num to ones if it's not in twos, otherwise remove it from ones
            ones = (ones ^ num) & ~twos;
            // Add current num to twos if it's not in ones, otherwise remove it from twos
            twos = (twos ^ num) & ~ones;
        }

        return ones; // The single number remains in 'ones'
    }

    // Test the solution
    public static void main(String[] args) {
        SignleNumberII sol = new SignleNumberII();

        int[] nums1 = {2, 2, 3, 2};
        System.out.println("Single number in [2, 2, 3, 2]: " + sol.singleNumber(nums1)); // 3

        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("Single number in [0, 1, 0, 1, 0, 1, 99]: " + sol.singleNumber(nums2)); // 99
    }
}
