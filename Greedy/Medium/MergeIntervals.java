package Greedy.Medium;

import java.util.*;

/*
✅ Problem Statement:
----------------------
You are given a list of intervals where each interval is [start, end].
You need to merge all overlapping intervals and return the resulting list of merged, non-overlapping intervals.

🔸 Example:
Input:  [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]

----------------------
✅ Intuition:
Since overlapping intervals will have overlapping start/end ranges, if we sort the intervals by their start time, 
we can then iterate and merge overlapping intervals in one pass.

----------------------
✅ Step-by-step Algorithm:
1. Sort the intervals by start time.
2. Initialize a result list and add the first interval.
3. For each interval:
   - If the current interval overlaps with the last one in result, merge them.
   - Else, add the current interval as a new entry to result.
4. Return the result list.

----------------------
✅ Time Complexity: O(N log N)
- For sorting the intervals.

✅ Space Complexity: O(N)
- For storing the result.

*/

class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;

        // To store the final merged intervals
        List<int[]> res = new ArrayList<>();

        // Step 1: Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Step 2: Initialize with the first interval
        int[] prev = intervals[0];

        // Step 3: Traverse and merge
        for (int i = 1; i < n; i++) {
            // If previous interval overlaps with current
            if (prev[1] >= intervals[i][0]) {
                // Merge by updating the end of previous interval
                prev[1] = Math.max(prev[1], intervals[i][1]);
            } else {
                // No overlap, add previous interval to result
                res.add(prev);
                prev = intervals[i]; // Move to the current interval
            }
        }

        // Add the last interval
        res.add(prev);

        // Convert to array and return
        return res.toArray(new int[res.size()][]);
    }

    // ✅ Main method for testing
    public static void main(String[] args) {
        MergeIntervals sol = new MergeIntervals();

        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Output 1: " + Arrays.deepToString(sol.merge(intervals1)));
        // Expected: [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.println("Output 2: " + Arrays.deepToString(sol.merge(intervals2)));
        // Expected: [[1, 5]]

        int[][] intervals3 = {{1, 10}, {2, 3}, {4, 5}, {6, 7}};
        System.out.println("Output 3: " + Arrays.deepToString(sol.merge(intervals3)));
        // Expected: [[1, 10]]
    }
}
