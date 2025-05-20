/*
✅ Problem:
Given an array of intervals where intervals[i] = [start_i, end_i], 
merge all overlapping intervals and return an array of the non-overlapping intervals that cover all the intervals in the input.

🧠 Intuition:
- If two intervals overlap, we can merge them by taking the min(start) and max(end).
- After sorting, overlapping intervals will always be next to each other.
- So we sort the intervals and then iterate through the array, merging as needed.

⚙️ Algorithm:
1. Sort intervals based on start time.
2. Initialize `curr` as the first interval.
3. For each next interval:
   - If `curr.end` < `next.start`, add `curr` to the result and update `curr` to `next`.
   - Else, merge by updating `curr.end` = max(`curr.end`, `next.end`)
4. Add the final `curr` interval to the result.

⏱ Time Complexity:
- O(N log N) for sorting + O(N) for merging = O(N log N)

📦 Space Complexity:
- O(N) for output list (excluding sorting overhead)
*/

package Array.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static List<List<Integer>> mergeOverlappingIntervals(int[][] intervals) {
        int n = intervals.length;

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<List<Integer>> res = new ArrayList<>();
        int[] curr = intervals[0];

        // Step 2: Traverse and merge intervals
        for (int i = 1; i < n; i++) {
            if (curr[1] < intervals[i][0]) {
                // No overlap
                res.add(Arrays.asList(curr[0], curr[1]));
                curr = intervals[i];
            } else {
                // Merge intervals
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }
        }

        // Step 3: Add last remaining interval
        res.add(Arrays.asList(curr[0], curr[1]));

        return res;
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 3 }, { 8, 10 }, { 2, 6 }, { 15, 18 } };
        List<List<Integer>> ans = mergeOverlappingIntervals(arr);
        System.out.print("The merged intervals are: \n");
        for (List<Integer> it : ans) {
            System.out.print("[" + it.get(0) + ", " + it.get(1) + "] ");
        }
        System.out.println();
    }
}
