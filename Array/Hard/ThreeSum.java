/*
✅ Problem: Three Sum
Given an array of integers `arr`, return all the triplets `[arr[i], arr[j], arr[k]]` such that:
- i ≠ j, i ≠ k, j ≠ k
- arr[i] + arr[j] + arr[k] == 0

🧠 Intuition:
We want to find 3 numbers whose sum is zero. By sorting the array, we can fix one number and use the two-pointer technique to find the remaining two numbers efficiently.

⚙️ Algorithm:
1. Sort the array.
2. For each element `arr[i]` from 0 to n-1:
   - Skip duplicates.
   - Use two pointers (`j = i+1`, `k = n-1`) to find pairs such that `arr[i] + arr[j] + arr[k] == 0`.
   - If the sum is zero, store the triplet and move both pointers, skipping duplicates.
   - If the sum is less than 0, move `j` forward.
   - If the sum is greater than 0, move `k` backward.

⏱ Time Complexity: 
- Sorting: O(n log n)
- Loop and two pointers: O(n^2)
=> Total: O(n^2)

📦 Space Complexity:
- O(1) (ignoring output space)
*/

package Array.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static List<List<Integer>> triplet(int n, int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr); // Step 1: Sort the array

        for (int i = 0; i < n; i++) {
            // Skip duplicates for the first number
            if (i != 0 && arr[i] == arr[i - 1]) continue;

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];

                if (sum < 0) {
                    j++;  // need a bigger sum
                } else if (sum > 0) {
                    k--;  // need a smaller sum
                } else {
                    // Found a valid triplet
                    ans.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    j++;
                    k--;

                    // Skip duplicates for j and k
                    while (j < k && arr[j] == arr[j - 1]) j++;
                    while (j < k && arr[k] == arr[k + 1]) k--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int n = arr.length;

        List<List<Integer>> ans = triplet(n, arr);

        System.out.println("The unique triplets that sum to 0 are:");
        for (List<Integer> it : ans) {
            System.out.println(it);
        }
    }
}
