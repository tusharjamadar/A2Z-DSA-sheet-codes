package DynamicProgramming.TwoDThreeD;
/*
✅ Problem Summary
Geek is training for n days. Each day he can do 1 of 3 activities:
- 0 → Running
- 1 → Fighting
- 2 → Learning

Points for each activity are given in arr[i][0], arr[i][1], arr[i][2].
Constraint: He cannot do the same activity on two consecutive days.
Goal: Find maximum total merit points.

🔁 Brute Force (Recursion)
- Try all activities each day, skipping the last chosen one.
- Explore all paths.
- Time Complexity: O(3^n) → TLE for n up to 1e5.

✅ Optimized Approaches
1. Memoization (Top-down DP)
   - Store answers in dp[day][last].
   - Time: O(N*4*3), Space: O(N*4 + recursion).

2. Tabulation (Bottom-up DP)
   - Build dp table iteratively.
   - Time: O(N*4*3), Space: O(N*4).

3. Space Optimization
   - Only need last row of DP at each step.
   - Time: O(N*4*3), Space: O(4).

🧪 Dry Run Example
arr = [[1,2,5], [3,1,1], [3,3,3]]
- Day 0: Pick Learning (5)
- Day 1: Pick Running (3)
- Day 2: Pick Fighting (3)
= 5+3+3 = 11 (max points)

⏱️ Time & Space Complexity
- Memoization: O(N*12), O(N*4)
- Tabulation: O(N*12), O(N*4)
- Space Optimized: O(N*12), O(4)
*/

import java.util.*;

class NinjaTraining {

    // 🔹 1. Recursion (Brute Force)
    private int helperRec(int day, int last, int[][] arr) {
        if (day == arr.length) return 0;

        int maxPoints = 0;
        for (int act = 0; act < 3; act++) {
            if (act == last) continue;
            int points = arr[day][act] + helperRec(day + 1, act, arr);
            maxPoints = Math.max(maxPoints, points);
        }
        return maxPoints;
    }
    public int maximumPointsRec(int[][] arr) {
        return helperRec(0, 3, arr); // 3 means "no last activity"
    }


    // 🔹 2. Memoization (Top-Down DP)
    private int helperMemo(int day, int last, int[][] arr, int[][] dp) {
        if (day == arr.length) return 0;
        if (dp[day][last] != -1) return dp[day][last];

        int maxPoints = 0;
        for (int act = 0; act < 3; act++) {
            if (act == last) continue;
            int points = arr[day][act] + helperMemo(day + 1, act, arr, dp);
            maxPoints = Math.max(maxPoints, points);
        }
        return dp[day][last] = maxPoints;
    }
    public int maximumPointsMemo(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][4];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperMemo(0, 3, arr, dp);
    }


    // 🔹 3. Tabulation (Bottom-Up DP)
    public int maximumPointsTab(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][4];

        // Base case: day 0
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);          // last=0 → max of act1, act2
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);          // last=1 → max of act0, act2
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);          // last=2 → max of act0, act1
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2])); // last=3 → any

        // Fill DP table
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int points = arr[day][task] + dp[day-1][task];
                        dp[day][last] = Math.max(dp[day][last], points);
                    }
                }
            }
        }
        return dp[n-1][3];
    }


    // 🔹 4. Space Optimized DP
    public int maximumPointsSpaceOpt(int[][] arr) {
        int n = arr.length;
        int[] prev = new int[4];

        // Base case: day 0
        prev[0] = Math.max(arr[0][1], arr[0][2]);
        prev[1] = Math.max(arr[0][0], arr[0][2]);
        prev[2] = Math.max(arr[0][0], arr[0][1]);
        prev[3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        // Iterate days
        for (int day = 1; day < n; day++) {
            int[] curr = new int[4];
            for (int last = 0; last < 4; last++) {
                curr[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int points = arr[day][task] + prev[task];
                        curr[last] = Math.max(curr[last], points);
                    }
                }
            }
            prev = curr.clone();
        }
        return prev[3];
    }


    // 🔹 Main for Testing
    public static void main(String[] args) {
        NinjaTraining sol = new NinjaTraining();

        int[][] arr1 = {{1,2,5}, {3,1,1}, {3,3,3}};
        int[][] arr2 = {{1,1,1}, {2,2,2}, {3,3,3}};
        int[][] arr3 = {{4,2,6}};

        System.out.println("Recursion: " + sol.maximumPointsRec(arr1));   // Expected 11
        System.out.println("Memoization: " + sol.maximumPointsMemo(arr1)); // Expected 11
        System.out.println("Tabulation: " + sol.maximumPointsTab(arr1));   // Expected 11
        System.out.println("Space Optimized: " + sol.maximumPointsSpaceOpt(arr1)); // Expected 11

        System.out.println("\nTest 2: " + sol.maximumPointsSpaceOpt(arr2)); // Expected 6
        System.out.println("Test 3: " + sol.maximumPointsSpaceOpt(arr3));   // Expected 6
    }
}
