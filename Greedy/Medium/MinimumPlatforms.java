package Greedy.Medium;
import java.util.*;

/*
✅ Problem Statement:
----------------------
Given arrival and departure times of trains at a station, determine the **minimum number of platforms** required 
so that no train waits due to platform unavailability.

Each platform can serve only one train at a time. If a train arrives before another departs, a new platform is needed.

----------------------
✅ Intuition:
Sort both arrival and departure arrays.
Use two pointers: one for arrival, one for departure.
Traverse through events:
- If a train arrives before the earliest departure, increase platforms.
- Else, a train has departed, free a platform.

Keep track of the max platforms used at any time.

----------------------
✅ Step-by-step Algorithm:
1. Sort both `arr` and `dep` arrays.
2. Initialize two pointers `i = 0` and `j = 0`.
3. Maintain two variables:
   - `platform`: current number of platforms in use.
   - `res`: maximum platforms used so far.
4. While `i < n`:
   - If `arr[i] <= dep[j]`, a train arrives → need a new platform.
     - Increment `platform`, `i`, and update `res`.
   - Else, a train departs → release a platform.
     - Decrement `platform`, increment `j`.

5. Return `res`.

----------------------
✅ Time Complexity: O(N log N)
- Due to sorting of arrival and departure arrays.

✅ Space Complexity: O(1)
- Only variables used for counting, no extra data structures.

*/

class MinimumPlatforms {
    static int findPlatform(int arr[], int dep[]) {
        int n = arr.length;

        // Step 1: Sort both arrays
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j = 0;
        int platform = 0;
        int res = 0;

        // Step 2: Traverse both arrays
        while (i < n) {
            if (arr[i] <= dep[j]) {
                // New train arrives before the current train departs
                platform++;
                res = Math.max(res, platform); // Track max platforms used
                i++;
            } else {
                // One train departs, release a platform
                platform--;
                j++;
            }
        }

        return res;
    }

    // ✅ Main method for testing
    public static void main(String[] args) {
        int[] arr1 = {900, 940, 950, 1100, 1500, 1800};
        int[] dep1 = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println("Output 1: " + findPlatform(arr1, dep1)); // Expected: 3

        int[] arr2 = {900, 1235, 1100};
        int[] dep2 = {1000, 1240, 1200};
        System.out.println("Output 2: " + findPlatform(arr2, dep2)); // Expected: 1

        int[] arr3 = {1000, 935, 1100};
        int[] dep3 = {1200, 1240, 1130};
        System.out.println("Output 3: " + findPlatform(arr3, dep3)); // Expected: 3
    }
}
