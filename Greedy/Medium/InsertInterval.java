package Greedy.Medium;
import java.util.*;

/*
✅ Problem Statement:
----------------------
You're given a sorted list of **non-overlapping intervals**, and a new interval to insert.
You must insert the new interval such that:
  - The list remains sorted by start times.
  - The list remains non-overlapping (merge if necessary).

🔸 Example:
Input:
  intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]]
  newInterval = [4,8]
Output:
  [[1,2],[3,10],[12,16]]
Because [4,8] overlaps with [3,5],[6,7],[8,10] -> merge to [3,10].

----------------------
✅ Intuition:
Since intervals are sorted by start time and non-overlapping:
- Add all intervals **ending before** the new interval starts → no merge needed.
- Merge all **overlapping intervals** with the new one → expand the newInterval boundaries.
- Add all remaining intervals after the new interval.

----------------------
✅ Step-by-step Algorithm:
1. Initialize result list `res`.
2. Traverse intervals:
   - While intervals[i][1] < newInterval[0] → no overlap, add to result.
   - While intervals[i][0] <= newInterval[1] → overlap, merge with newInterval.
3. Add the merged newInterval to result.
4. Add remaining intervals.
5. Return result as array.

----------------------
✅ Time Complexity: O(N)
- N = number of intervals.
- Each interval is visited at most once.

✅ Space Complexity: O(N)
- Result list stores all intervals.

*/

class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        int i = 0, n = intervals.length;

        // Step 1: Add all intervals that end before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval); // Add the merged interval

        // Step 3: Add remaining intervals
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }

    // ✅ Main method to test the code
    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();

        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        System.out.println("Output 1: " + Arrays.deepToString(sol.insert(intervals1, newInterval1)));
        // Expected: [[1,5],[6,9]]

        int[][] intervals2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval2 = {4,8};
        System.out.println("Output 2: " + Arrays.deepToString(sol.insert(intervals2, newInterval2)));
        // Expected: [[1,2],[3,10],[12,16]]

        int[][] intervals3 = {};
        int[] newInterval3 = {5,7};
        System.out.println("Output 3: " + Arrays.deepToString(sol.insert(intervals3, newInterval3)));
        // Expected: [[5,7]]
    }
}
