package BinarySearch.OnAnswers;
/*
Problem: Aggressive Cows (Binary Search on Answer)

You are given an array of stall positions and a number 'k' representing the number of cows.
Place the cows in the stalls such that the minimum distance between any two cows is maximized.

Intuition:
- The optimal solution lies between 1 and the max possible distance (max - min of stalls).
- We use Binary Search on this answer range.
- For a mid value (potential distance), we check if we can place all k cows such that 
  each cow is at least `mid` distance apart.

Approach:
1. Sort the stall positions.
2. Use Binary Search between 1 and (last - first stall position).
3. For each mid, check if it’s possible to place k cows with at least `mid` distance.
   - Greedily place cows by scanning the stalls and placing them as far apart as possible.
4. If possible, try to increase distance (move right).
5. Else, reduce the distance (move left).

Time Complexity: O(N log(max_dist))  
- N: number of stalls
- log(max_dist): binary search range

Space Complexity: O(1)

*/

import java.util.Arrays;

class AggressiveCows {

    private static boolean canPlace(int[] stalls, int cows, int dist) {
        int count = 1, last = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= dist) {
                count++;
                last = stalls[i];
            }
            if (count == cows) return true;
        }

        return false;
    }

    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);

        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlace(stalls, k, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // Test code
    public static void main(String[] args) {
        System.out.println(aggressiveCows(new int[]{1, 2, 4, 8, 9}, 3)); // Output: 3
        System.out.println(aggressiveCows(new int[]{10, 1, 2, 7, 5}, 3)); // Output: 4
        System.out.println(aggressiveCows(new int[]{2, 12, 11, 3, 26, 7}, 5)); // Output: 1
    }
}

