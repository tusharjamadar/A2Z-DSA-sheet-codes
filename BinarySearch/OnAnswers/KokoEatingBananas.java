package BinarySearch.OnAnswers;
/*
Problem: 875. Koko Eating Bananas

Koko loves to eat bananas. Given an array 'piles' where each element represents the number of bananas in a pile,
and an integer 'h' representing total hours Koko has, find the minimum integer eating speed 'k' such that she can
eat all bananas within 'h' hours.

Rules:
- Each hour, Koko can eat up to 'k' bananas from a pile.
- If the pile has fewer than 'k' bananas, she eats the entire pile in that hour.
- Each hour she can only eat from one pile.

Approach:
1. Use **binary search** to find the minimum speed:
   - Lower bound (low) = 1 (slowest speed).
   - Upper bound (high) = max(piles) (fastest possible speed, finishing the largest pile in 1 hour).
2. For a candidate speed 'mid', check if it's possible to eat all bananas within 'h' hours.
3. Adjust search boundaries based on feasibility:
   - If possible, try a smaller speed (move left).
   - Else, need a higher speed (move right).

Helper Function:
- isPossible(piles, mid, h):
   - Calculate the total time taken if Koko eats at speed 'mid'.
   - If total hours <= h, it's possible.

Time Complexity: O(n * log(max(piles)))
- n = number of piles
- log(max(piles)) for binary search steps

Space Complexity: O(1)
- Only using a few extra variables.

*/

class KokoEatingBananas {

    // Helper function to check if eating speed 'mid' can finish bananas within 'h' hours
    public static boolean isPossible(int[] piles, int mid, int h) {
        int cnt = 0; // total hours used

        for (int i = 0; i < piles.length; i++) {
            cnt += (piles[i] + mid - 1) / mid; // Ceiling division without using Math.ceil
            if (cnt > h) return false; // Early exit if already exceeding h hours
        }
        return true;
    }

    // Main function to find minimum eating speed
    public static int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int maxi = Integer.MIN_VALUE;

        // Find the maximum bananas in any pile
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, piles[i]);
        }

        int low = 1, high = maxi;
        int res = Integer.MAX_VALUE;

        // Binary search for minimum valid speed
        while (low <= high) {
            int mid = (low + high) / 2;

            if (isPossible(piles, mid, h)) {
                res = Math.min(res, mid); // Update result and search in lower half
                high = mid - 1;
            } else {
                low = mid + 1; // Search in higher half
            }
        }
        return res;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Minimum eating speed: " + minEatingSpeed(piles1, h1)); // Output: 4

        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Minimum eating speed: " + minEatingSpeed(piles2, h2)); // Output: 30

        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Minimum eating speed: " + minEatingSpeed(piles3, h3)); // Output: 23
    }
}

