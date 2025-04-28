package BinarySearch.OnAnswers;
/*
Problem: 1482. Minimum Number of Days to Make m Bouquets

You are given an integer array bloomDay, an integer m, and an integer k.
You want to make m bouquets. To make one bouquet, you need k adjacent bloomed flowers.
Each flower blooms exactly on bloomDay[i] day. 
Return the minimum number of days needed to make m bouquets, else return -1 if impossible.

Intuition:
- We need to find the minimum day such that enough adjacent flowers are bloomed.
- It's a classic "minimum satisfying value" problem -> apply Binary Search on answer.

Approach:
1. Use Binary Search on the number of days.
2. For each day (mid), check if we can make m bouquets:
   - Traverse bloomDay array, count adjacent bloomed flowers.
   - Whenever we get k adjacent flowers, make a bouquet (decrease m).
3. If we can make m bouquets, search for smaller days (high = mid - 1).
   If not, search for bigger days (low = mid + 1).
4. If we never find enough bouquets, return -1.

Edge Cases:
- If total flowers < m * k, immediately return -1 (not enough flowers).

Helper Function:
- `isPossible()`: checks if we can form m bouquets within given `mid` days.

Time Complexity: O(n * log(max(bloomDay)))
Space Complexity: O(1)

*/

class MinimumNumberOfDaysMakeBouquets {

    // Helper function to check if it is possible to make m bouquets by 'mid' day
    private static boolean isPossible(int[] bloomDay, int m, int k, int mid) {
        int cnt = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                cnt++;
                if (cnt == k) {
                    m--;
                    cnt = 0;
                    if (m == 0) return true;
                }
            } else {
                cnt = 0;
            }
        }
        return m == 0;
    }

    // Main logic to find minimum days
    public static int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        
        // Edge Case: Not enough flowers
        if ((long) m * k > n) return -1;

        int maxi = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            maxi = Math.max(maxi, day);
        }

        int low = 1, high = maxi;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(bloomDay, m, k, mid)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    // Main function to test locally
    public static void main(String[] args) {
        int[] bloomDay1 = {1, 10, 3, 10, 2};
        System.out.println(minDays(bloomDay1, 3, 1)); // Output: 3

        int[] bloomDay2 = {1, 10, 3, 10, 2};
        System.out.println(minDays(bloomDay2, 3, 2)); // Output: -1

        int[] bloomDay3 = {7, 7, 7, 7, 12, 7, 7};
        System.out.println(minDays(bloomDay3, 2, 3)); // Output: 12

        int[] bloomDay4 = {5, 5, 5, 5, 5};
        System.out.println(minDays(bloomDay4, 1, 5)); // Output: 5
    }
}
