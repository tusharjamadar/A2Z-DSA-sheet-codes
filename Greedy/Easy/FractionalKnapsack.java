package Greedy.Easy;
import java.util.*;

/**
 * Problem: Fractional Knapsack
 * -----------------------------
 * Given:
 *  - values[i]: value of the i-th item
 *  - weights[i]: weight of the i-th item
 *  - W: total capacity of the knapsack
 *
 * You can break items to take fractional parts.
 * Goal: Maximize the total value in the knapsack.
 *
 * Example:
 * Input: values = [60, 100, 120], weights = [10, 20, 30], W = 50
 * Output: 240.000000
 *
 * Explanation:
 * Take full items of 60 and 100, and 2/3 of 120 => 60 + 100 + 80 = 240.0
 */

//  Time Complexity: O(n log n) due to sorting

// Space Complexity: O(n) for storing item pairs

class FractionalKnapsack {

    /**
     * Function to compute the maximum total value in the knapsack using fractional items
     *
     * Intuition:
     * - Greedy algorithm: Always choose the item with the highest value-to-weight ratio first.
     * - Sort all items by (value / weight) in descending order.
     * - Take as much as possible from the top item until capacity is full.
     *
     * @param values  : array of item values
     * @param weights : array of item weights
     * @param W       : capacity of the knapsack
     * @return maximum value (double) that can be put in the knapsack
     *
     * Time Complexity: O(n log n) — sorting the items by value/weight ratio
     * Space Complexity: O(n) — storing item pairs
     */
    public double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n = values.length;

        // Step 1: Pair values and weights into a 2D array
        int[][] items = new int[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = values[i];    // value
            items[i][1] = weights[i];   // weight
        }

        // Step 2: Sort items based on value-to-weight ratio (descending)
        Arrays.sort(items, (a, b) -> Double.compare(
                (double) b[0] / b[1],
                (double) a[0] / a[1]
        ));

        double totalValue = 0.0;

        // Step 3: Traverse items in sorted order and add to knapsack
        for (int i = 0; i < n && W > 0; i++) {
            int val = items[i][0];
            int wt = items[i][1];

            if (wt <= W) {
                // Take full item
                totalValue += val;
                W -= wt;
            } else {
                // Take fraction of item
                totalValue += ((double) val / wt) * W;
                W = 0; // knapsack is full
            }
        }

        return totalValue;
    }

    /**
     * Main method to test the fractional knapsack function with sample inputs
     */
    public static void main(String[] args) {
        FractionalKnapsack solver = new FractionalKnapsack();

        // Test Case 1
        int[] val1 = {60, 100, 120};
        int[] wt1 = {10, 20, 30};
        int W1 = 50;
        // Expected Output: 240.000000
        System.out.printf("Max Value (Test 1): %.6f\n", solver.fractionalKnapsack(val1, wt1, W1));

        // Test Case 2
        int[] val2 = {60, 100};
        int[] wt2 = {10, 20};
        int W2 = 50;
        // Expected Output: 160.000000
        System.out.printf("Max Value (Test 2): %.6f\n", solver.fractionalKnapsack(val2, wt2, W2));

        // Test Case 3
        int[] val3 = {10, 20, 30};
        int[] wt3 = {5, 10, 15};
        int W3 = 100;
        // Expected Output: 60.000000
        System.out.printf("Max Value (Test 3): %.6f\n", solver.fractionalKnapsack(val3, wt3, W3));
    }
}
