package Greedy.Medium;

public class Candy {

    /*

    Solution 1:

    ✅ Problem: Distribute candies based on neighbor ratings.

    ✅ Intuition:
    Use two passes:
    - Left-to-right: if current rating > previous, increase candies.
    - Right-to-left: if current rating > next, increase candies accordingly.

    ✅ Algorithm:
    1. Create left[] array of size n, initialize left[0] = 1
    2. Traverse left to right, increase candies when rating increases
    3. Traverse right to left, compute sum using max of both directions
    4. Return the total sum

    ✅ Time Complexity: O(2N)
    ✅ Space Complexity: O(N)
    */
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        left[0] = 1;

        // Left to Right pass
        for(int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Right to Left pass and compute sum
        int sum = left[n-1], right = 1;
        for(int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                right += 1;
            } else {
                right = 1;
            }
            sum += Math.max(left[i], right);
        }

        return sum;
    }

    /*
    ✅ Solution 2

    ✅ Intuition:
    Traverse once and handle slopes:
    - For increasing slope: keep incrementing candies.
    - For decreasing slope: count slope length and sum it.
    - Track peak overlap (child counted in both directions) and correct it.

    ✅ Algorithm:
    1. Start with one candy
    2. For equal ratings → give 1 candy
    3. For increasing ratings → increase peak count
    4. For decreasing ratings → count down length
    5. At transition, adjust peak if down slope is longer

    ✅ Time Complexity: O(N)
    ✅ Space Complexity: O(1)
    */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int sum = 1;   // first child gets 1
        int i = 1;

        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                sum += 1; // equal rating → give 1
                i++;
                continue;
            }

            int peak = 1;
            // Handle increasing slope
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak += 1;
                sum += peak;
                i++;
            }

            int down = 1;
            // Handle decreasing slope
            while (i < n && ratings[i] < ratings[i - 1]) {
                sum += down;
                down++;
                i++;
            }

            // Correct peak counted twice
            if (down > peak) {
                sum += down - peak;
            }
        }

        return sum;
    }
    
    public static void main(String[] args) {
        Candy sol = new Candy();

        int[] r1 = {1, 0, 2};
        int[] r2 = {1, 2, 2};
        int[] r3 = {1, 3, 4, 5, 2};

        System.out.println(sol.candy1(r1)); // Output: 5
        System.out.println(sol.candy2(r2)); // Output: 4
        System.out.println(sol.candy1(r3)); // Output: 11
    }
}
