package Greedy.Medium;

/*
✅ Problem: Job Sequencing Problem
-----------------------------------
You are given `n` jobs with deadlines and profits. Each job takes 1 unit of time and must be completed before or on its deadline to earn profit.
Only one job can be executed at a time.

Goal:
1. Maximize number of jobs done.
2. Maximize total profit from scheduled jobs.

-----------------------------------
Example:
Input:
    deadline = [4, 1, 1, 1]
    profit = [20, 10, 40, 30]

Output:
    [2, 60]
Explanation:
    We can do Job3 (profit 40) and Job1 (profit 20).

-----------------------------------
✅ Intuition:
- Sort jobs by descending profit.
- Try to schedule each job at the **latest time slot before its deadline**.
- Greedily choose most profitable available job slots.

-----------------------------------
✅ Algorithm:
1. Create a 2D array `jobs[][]` storing [deadline, profit].
2. Sort jobs by profit in descending order.
3. Create a slot availability array `hash[]` of size maxDeadline + 1 initialized to -1.
4. For each job:
   - Try to place it in the latest slot <= deadline.
   - If a free slot is found, assign job and add its profit.
5. Return [totalJobs, totalProfit].

-----------------------------------
✅ Time Complexity:
- Sorting: O(n log n)
- Looping through jobs: O(n * D) in worst case (D = max deadline)
  But typically D ≪ n so it's efficient for practical inputs.

✅ Space Complexity: O(n + D)
- For job array and deadline availability hash
*/

import java.util.*;

class JobSequencingProblem {

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;

        // Step 1: Pair deadlines and profits
        int[][] jobs = new int[n][2];
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadline[i]; // deadline
            jobs[i][1] = profit[i];   // profit
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        // Step 2: Sort jobs by descending profit
        Arrays.sort(jobs, (a, b) -> b[1] - a[1]);

        // Step 3: Track available time slots (1-indexed)
        int[] hash = new int[maxDeadline + 1];
        Arrays.fill(hash, -1);  // -1 means slot is free

        int totalJobs = 0, totalProfit = 0;

        // Step 4: Try to assign each job to a free slot ≤ its deadline
        for (int i = 0; i < n; i++) {
            int d = jobs[i][0]; // deadline
            int p = jobs[i][1]; // profit

            // Try to schedule at the latest free time ≤ deadline
            for (int j = d; j > 0; j--) {
                if (hash[j] == -1) {
                    hash[j] = i;      // Mark slot occupied
                    totalJobs++;
                    totalProfit += p;
                    break;
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.add(totalJobs);
        res.add(totalProfit);
        return res;
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        JobSequencingProblem sol = new JobSequencingProblem();

        int[] deadline1 = {4, 1, 1, 1};
        int[] profit1 = {20, 10, 40, 30};
        System.out.println("Output 1: " + sol.jobSequencing(deadline1, profit1)); // [2, 60]

        int[] deadline2 = {2, 1, 2, 1, 1};
        int[] profit2 = {100, 19, 27, 25, 15};
        System.out.println("Output 2: " + sol.jobSequencing(deadline2, profit2)); // [2, 127]

        int[] deadline3 = {3, 1, 2, 2};
        int[] profit3 = {50, 10, 20, 30};
        System.out.println("Output 3: " + sol.jobSequencing(deadline3, profit3)); // [3, 100]
    }
}
