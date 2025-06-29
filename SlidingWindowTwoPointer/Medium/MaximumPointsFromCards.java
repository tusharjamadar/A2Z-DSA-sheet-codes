package SlidingWindowTwoPointer.Medium;

/*
 * ✅ Problem: 1423. Maximum Points You Can Obtain from Cards
 * You are given an array of integers `cardPoints` and an integer `k`.
 * You can take exactly k cards from either end (start or end) of the array.
 * Return the maximum score you can obtain.
 *
 * 💡 Intuition:
 * - You must take `k` cards in total.
 * - Instead of choosing `k` cards directly, think of the `n-k` **middle** cards you're NOT taking.
 * - Find the **minimum sum** of any subarray of length `n-k` and subtract it from the total sum.
 * - But this solution uses a **sliding window approach**, where we try every possible split between front and back:
 *   take i cards from the left and k-i cards from the right.
 *
 * 🧠 Algorithm:
 * 1. Compute the sum of the first `k` cards (take all from the start).
 * 2. Move a window backward:
 *    - Remove cards from the left and add from the right (simulate shift from front to back).
 *    - At each step, update the maximum sum.
 * 3. Return the maximum sum found.
 *
 * ⏱️ Time Complexity: O(k)
 * 🗃️ Space Complexity: O(1)
 *
 * 🔁 Dry Run:
 * cardPoints = [1,2,3,4,5,6,1], k = 3
 * Start with leftSum = 1+2+3 = 6
 * Try taking from back:
 *  i = 2 → leftSum = 1+2 = 3, rightSum = 1 → total = 4
 *  i = 1 → leftSum = 1 = 1, rightSum = 1+6 = 7 → total = 8
 *  i = 0 → leftSum = 0, rightSum = 1+6+5 = 12 → total = 12 ← max
 */

class MaximumPointsFromCards {
    public int maxScore(int[] cardPoints, int k) {
        int leftSum = 0, rightSum = 0;
        int n = cardPoints.length;

        // Take first k cards from the left
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }

        int maxSum = leftSum;
        int rightIndex = n - 1;

        // Slide window: remove from left, add from right
        for (int i = k - 1; i >= 0; i--) {
            leftSum -= cardPoints[i];             // remove from left
            rightSum += cardPoints[rightIndex];   // add from right
            rightIndex--;

            maxSum = Math.max(maxSum, leftSum + rightSum); // update max
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumPointsFromCards sol = new MaximumPointsFromCards();

        int[] cardPoints1 = {1, 2, 3, 4, 5, 6, 1};
        int k1 = 3;
        System.out.println("Test Case 1: " + sol.maxScore(cardPoints1, k1)); // Output: 12

        int[] cardPoints2 = {2, 2, 2};
        int k2 = 2;
        System.out.println("Test Case 2: " + sol.maxScore(cardPoints2, k2)); // Output: 4

        int[] cardPoints3 = {9, 7, 7, 9, 7, 7, 9};
        int k3 = 7;
        System.out.println("Test Case 3: " + sol.maxScore(cardPoints3, k3)); // Output: 55
    }
}
