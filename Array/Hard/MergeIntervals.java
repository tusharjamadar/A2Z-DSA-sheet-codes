import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    // Time Complexity: O(N*logN) + O(N), where N = the size of the given array.
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] intervals) {
        int n = intervals.length;

        // Sort array according to start interval time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Declare a 2D array for answer
        List<List<Integer>> res = new ArrayList<>();
        int[] curr = intervals[0];

        for (int i = 1; i < n; i++) {
            // curr end is smaller than next start then should be add that interval till
            // that
            if (curr[1] < intervals[i][0]) {
                res.add(Arrays.asList(curr[0], curr[1]));
                curr = intervals[i];
            } else {
                // update curr end with maximun end interval value
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }
        }
        // add last remaining interval
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
