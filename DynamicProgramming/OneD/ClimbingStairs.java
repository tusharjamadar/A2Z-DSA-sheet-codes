package DynamicProgramming.OneD;
/*
====================================================================
Problem: 70. Climbing Stairs
====================================================================
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways 
can you climb to the top?

--------------------------------------------------------------------
Example 1:
Input: n = 2
Output: 2
Explanation: (1+1), (2)

Example 2:
Input: n = 3
Output: 3
Explanation: (1+1+1), (1+2), (2+1)

Constraints: 
1 <= n <= 45
====================================================================
We solve it in 4 ways:
1. Recursion
2. Memoization (Top-Down DP)
3. Tabulation (Bottom-Up DP)
4. Space Optimization
====================================================================
*/


import java.util.*;

class ClimbingStairs {

    /*--------------------------------------------------------------
    1. RECURSION
    --------------------------------------------------------------
    Intuition:
    - To reach step n, you can come from step (n-1) or (n-2).
    - So ways(n) = ways(n-1) + ways(n-2).

    Algorithm:
    - Base case: ways(1) = 1, ways(2) = 2
    - Recursive relation: f(n) = f(n-1) + f(n-2)

    Time Complexity: O(2^n)  -> exponential (overlapping subproblems)
    Space Complexity: O(n)   -> recursion call stack

    Dry Run (n=3):
    f(3) = f(2) + f(1)
         = (2) + (1)
         = 3
    --------------------------------------------------------------*/
    public int climbStairsRecursion(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        return climbStairsRecursion(n-1) + climbStairsRecursion(n-2);
    }

    /*--------------------------------------------------------------
    2. MEMOIZATION (Top-Down DP)
    --------------------------------------------------------------
    Intuition:
    - Same as recursion, but we store results to avoid recomputation.

    Algorithm:
    - Create dp array initialized with -1.
    - If result already computed, return it.
    - Otherwise, compute recursively and store.

    Time Complexity: O(n)
    Space Complexity: O(n) (dp array + recursion stack)

    Dry Run (n=3):
    f(3) = f(2) + f(1)
         -> f(2)=2 stored in dp
         -> f(1)=1 stored in dp
         => f(3)=3 stored in dp
    --------------------------------------------------------------*/
    private int helperMemo(int n, int[] dp){
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(dp[n] != -1) return dp[n];
        return dp[n] = helperMemo(n-1, dp) + helperMemo(n-2, dp);
    }
    public int climbStairsMemo(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return helperMemo(n, dp);
    }

    /*--------------------------------------------------------------
    3. TABULATION (Bottom-Up DP)
    --------------------------------------------------------------
    Intuition:
    - Build the dp table from smaller subproblems (iterative).

    Algorithm:
    - dp[1] = 1, dp[2] = 2
    - For i=3..n: dp[i] = dp[i-1] + dp[i-2]
    - Answer = dp[n]

    Time Complexity: O(n)
    Space Complexity: O(n)

    Dry Run (n=3):
    dp[1]=1, dp[2]=2
    dp[3]=dp[2]+dp[1]=2+1=3
    --------------------------------------------------------------*/
    public int climbStairsTab(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /*--------------------------------------------------------------
    4. SPACE OPTIMIZATION
    --------------------------------------------------------------
    Intuition:
    - We only need last two states, no need to store whole array.

    Algorithm:
    - prev2=1, prev1=2
    - Loop from 3..n:
         curr=prev1+prev2
         shift prev2=prev1, prev1=curr
    - Return prev1

    Time Complexity: O(n)
    Space Complexity: O(1)

    Dry Run (n=3):
    prev2=1, prev1=2
    i=3: curr=2+1=3 → prev1=3
    Answer=3
    --------------------------------------------------------------*/
    public int climbStairsSpaceOpt(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int prev2 = 1, prev1 = 2;
        for(int i=3; i<=n; i++){
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /*--------------------------------------------------------------
    MAIN METHOD TO TEST ALL APPROACHES
    --------------------------------------------------------------*/
    public static void main(String[] args) {
        ClimbingStairs sol = new ClimbingStairs();
        int n1 = 2, n2 = 3, n3 = 5;

        System.out.println("Recursion n=3: " + sol.climbStairsRecursion(n2));   // 3
        System.out.println("Memoization n=3: " + sol.climbStairsMemo(n2));     // 3
        System.out.println("Tabulation n=3: " + sol.climbStairsTab(n2));       // 3
        System.out.println("SpaceOpt n=3: " + sol.climbStairsSpaceOpt(n2));    // 3

        System.out.println("Recursion n=2: " + sol.climbStairsRecursion(n1));  // 2
        System.out.println("Memoization n=5: " + sol.climbStairsMemo(n3));     // 8
        System.out.println("Tabulation n=5: " + sol.climbStairsTab(n3));       // 8
        System.out.println("SpaceOpt n=5: " + sol.climbStairsSpaceOpt(n3));    // 8
    }
}
