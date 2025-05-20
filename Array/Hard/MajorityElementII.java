/*
🔢 Problem:
Given an integer array `nums`, return all the elements that appear more than ⌊ n/3 ⌋ times.

🧠 Intuition:
There can be **at most two elements** in the array that appear more than n/3 times.
This is an extension of the Boyer-Moore Majority Vote algorithm (used for n/2 case).

We find two potential candidates and then verify if they occur more than n/3 times.

⚙️ Algorithm:
1. Initialize two candidates (e1, e2) and their counts (c1, c2).
2. Traverse the array:
    - If current element matches a candidate, increment its count.
    - Else if any count is zero, assign new candidate and set count = 1.
    - Else, decrement both counts.
3. Traverse again to count the actual occurrences of e1 and e2.
4. If count > n/3, add to result list.

⏱️ Time Complexity:
- O(n) for two passes through the array

📦 Space Complexity:
- O(1) extra space (excluding the result list)
*/

package Array.Hard;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    public static List<Integer> majorityElement(int[] nums) {
        if (nums == null) return new ArrayList<>();

        int n = nums.length;
        int e1 = Integer.MIN_VALUE, c1 = 0;
        int e2 = Integer.MIN_VALUE, c2 = 0;

        // 🔁 First Pass: Find potential candidates
        for (int i = 0; i < n; i++) {
            if (c1 == 0 && nums[i] != e2) {
                e1 = nums[i];
                c1 = 1;
            } else if (c2 == 0 && nums[i] != e1) {
                e2 = nums[i];
                c2 = 1;
            } else if (nums[i] == e1) {
                c1++;
            } else if (nums[i] == e2) {
                c2++;
            } else {
                c1--;
                c2--;
            }
        }

        // ✅ Second Pass: Verify actual counts
        c1 = 0;
        c2 = 0;
        for (int num : nums) {
            if (num == e1) c1++;
            if (num == e2) c2++;
        }

        List<Integer> majorityElements = new ArrayList<>();
        if (c1 > n / 3) majorityElements.add(e1);
        if (e2 != e1 && c2 > n / 3) majorityElements.add(e2);

        return majorityElements;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,7,7,8,8,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3};
        System.out.println("Majority elements (> n/3 times): " + majorityElement(arr));
    }
}
