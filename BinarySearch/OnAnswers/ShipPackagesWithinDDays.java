package BinarySearch.OnAnswers;
/*
Problem: 1011. Capacity To Ship Packages Within D Days

You are given an array 'weights' where weights[i] represents the weight of the ith package.
You must ship all packages within 'days' days, following the original order.
Each day, you load the ship without exceeding the ship's weight capacity.

Return the minimum possible ship capacity needed to ship all packages in 'days' days.

Intuition:
- We need to find the smallest ship capacity to ship all packages within 'days'.
- If a ship has more capacity, it can carry more items and finish faster (Monotonic Behavior).
- Hence, apply Binary Search on the answer space: ship's capacity.

Approach:
1. Binary Search between [max(weights), sum(weights)]:
   - Minimum ship capacity must be at least the heaviest package.
   - Maximum ship capacity can be sum of all packages (all in one day).
2. For each mid (capacity), check if it's possible to ship within 'days'.
   - Traverse weights and simulate shipping.
   - If a day's load exceeds mid, move to next day.
   - Count how many days needed.
3. Adjust search range based on whether the mid was sufficient or not.

Helper Function:
- `isPossible()`: returns true if given ship capacity 'mid' can ship within 'days'.

Time Complexity: O(n * log(sum(weights) - max(weights)))
Space Complexity: O(1)

*/

class ShipPackagesWithinDDays {

    // Helper function to check if capacity 'mid' can ship all packages within 'days'
    private static boolean isPossible(int[] weights, int days, int mid) {
        int dayCount = 1;
        int currentLoad = 0;
        
        for (int weight : weights) {
            currentLoad += weight;
            
            if (currentLoad > mid) {
                dayCount++;
                currentLoad = weight;
            }
        }
        return dayCount <= days;
    }

    // Main logic to find minimum ship capacity
    public static int shipWithinDays(int[] weights, int days) {
        int maxWeight = Integer.MIN_VALUE;
        int totalWeight = 0;

        for (int weight : weights) {
            totalWeight += weight;
            maxWeight = Math.max(maxWeight, weight);
        }

        int low = maxWeight, high = totalWeight;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(weights, days, mid)) {
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
        int[] weights1 = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(shipWithinDays(weights1, 5)); // Output: 15

        int[] weights2 = {3,2,2,4,1,4};
        System.out.println(shipWithinDays(weights2, 3)); // Output: 6

        int[] weights3 = {1,2,3,1,1};
        System.out.println(shipWithinDays(weights3, 4)); // Output: 3

        int[] weights4 = {5,5,5,5,5,5};
        System.out.println(shipWithinDays(weights4, 2)); // Output: 15
    }
}
