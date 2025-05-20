/*
🔢 Problem:
Implement the next lexicographical permutation of the given integer array.
If there is no next permutation, rearrange the array into the lowest possible order (sorted in ascending order).

🧠 Intuition:
To find the next permutation:
1. Traverse from the right to find the first index `ind1` such that nums[ind1] < nums[ind1 + 1].
   - This is the "breaking point" where the order starts decreasing.
2. If such an index doesn't exist, the array is in descending order. So, reverse the entire array.
3. If `ind1` is found:
   - Find the smallest number to the right of `ind1` that is greater than nums[ind1] (call it `ind2`) and swap them.
   - Reverse the right subarray after `ind1` to get the next smallest lexicographic permutation.

⚙️ Algorithm:
- Traverse from right to find the first decreasing index.
- If not found, reverse the array.
- If found:
   - Find the element just larger than nums[ind1] from the end.
   - Swap them.
   - Reverse the suffix starting from ind1 + 1.

⏱️ Time Complexity:
- O(n) where n = length of array
- We perform at most two traversals of the array.

📦 Space Complexity:
- O(1) since the operations are in-place.
*/

package Array.Medium;

import java.util.Arrays;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int ind1 = -1, ind2 = -1;

        // Step 1: Find the breaking point
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind1 = i;
                break;
            }
        }

        // If no breaking point is found, reverse entire array
        if (ind1 == -1) {
            reverse(nums, 0);
        } else {
            // Step 2: Find element just greater than nums[ind1] and swap
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] > nums[ind1]) {
                    ind2 = i;
                    break;
                }
            }

            swap(nums, ind1, ind2);

            // Step 3: Reverse the right half
            reverse(nums, ind1 + 1);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;

        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3 };
        nextPermutation(arr);
        System.out.print(Arrays.toString(arr)); // Output: [1, 3, 2]
    }
}
