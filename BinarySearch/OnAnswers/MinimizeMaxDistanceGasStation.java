package BinarySearch.OnAnswers;

class MinimizeMaxDistanceGasStation {

    // 💬 Problem:
    // Given an array of gas station positions (in increasing order) and an integer k,
    // add k additional gas stations such that the maximum distance between any two 
    // adjacent stations is minimized. Return this minimum distance to 2 decimal places.

    // 💡 Intuition:
    // The largest gap between any two stations determines the maximum inconvenience.
    // We can reduce this by adding stations between the gaps.
    // For a fixed maximum allowed distance `d`, we can calculate how many stations are
    // needed to keep all distances ≤ d.
    // We apply Binary Search on decimal values to find the smallest such `d` 
    // where total added stations ≤ k.

    // 🧮 Helper function to calculate the number of stations needed to ensure
    // all gaps between adjacent stations are <= dist
    public static int numberOfStationRequired(double dist, int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            double gap = arr[i] - arr[i - 1];
            // Number of stations to be added in the current segment
            count += (int)(gap / dist);
        }
        return count;
    }

    // 🧩 Main function to find the smallest maximum distance possible
    public static double findSmallestMaxDist(int[] arr, int k) {
        int n = arr.length;
        double low = 0, high = 0;

        // 🔍 Identify the maximum existing gap between any two adjacent stations
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        // 🎯 Binary search to find the minimum possible maximum distance
        double precision = 1e-6;
        while (high - low > precision) {
            double mid = (low + high) / 2.0;

            // Check how many stations are required for this mid distance
            int required = numberOfStationRequired(mid, arr);
            if (required > k) {
                // Too many stations needed → mid is too small
                low = mid;
            } else {
                // Fewer or equal stations needed → try smaller distance
                high = mid;
            }
        }

        // 🧾 Round the result to 2 decimal places as required
        return Math.round(high * 100.0) / 100.0;
    }

    // ✅ Example test
    public static void main(String[] args) {
        int[] stations1 = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("Output: " + findSmallestMaxDist(stations1, 9)); // Expected: 0.50

        int[] stations2 = {3,6,12,19,33,44,67,72,89,95};
        System.out.println("Output: " + findSmallestMaxDist(stations2, 2)); // Expected: 14.00
    }
}
