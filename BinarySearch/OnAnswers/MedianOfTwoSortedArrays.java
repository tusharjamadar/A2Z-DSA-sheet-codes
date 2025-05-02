package BinarySearch.OnAnswers;

class MedianOfTwoSortedArrays {

    /*
    ✅ Problem:
    Given two sorted arrays nums1 and nums2, return the median of the combined sorted array.
    The overall runtime complexity should be O(log(min(m, n))) where m and n are sizes of the arrays.

    🧠 Intuition:
    We want to find a partition between the two arrays such that:
    - The left half contains the same number of (or one more) elements than the right half.
    - All elements on the left are <= all elements on the right.

    This is a classic binary search problem. We binary search on the smaller array to find the correct partition.

    🧮 Algorithm:
    1. Always binary search on the smaller array to minimize time.
    2. Use binary search to choose a partition in the first array (nums1).
       The corresponding partition in the second array (nums2) is determined.
    3. At each step, check if max(left) ≤ min(right) on both sides.
       - If yes: we've found the correct partition.
       - If not: adjust binary search boundaries accordingly.
    4. Calculate the median based on whether the total length is odd or even.
    */

    public static double findMedianSortedArrays(int[] a, int[] b) {
        int n1 = a.length, n2 = b.length;

        // ⚙️ Step 1: Ensure binary search is on the smaller array
        if (n1 > n2) return findMedianSortedArrays(b, a);

        int n = n1 + n2;           // Total length of combined arrays
        int left = (n + 1) / 2;    // Length of the left half when merged

        // 🔍 Step 2: Binary search on the smaller array
        int low = 0, high = n1;

        while (low <= high) {
            int mid1 = (low + high) / 2;     // Partition index in first array
            int mid2 = left - mid1;          // Partition index in second array

            // Edge values for comparison (handle out-of-bound with MIN/MAX values)
            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? b[mid2] : Integer.MAX_VALUE;

            // ✅ Step 3: Valid partition found
            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) {
                    // Odd length: return max of left side
                    return Math.max(l1, l2);
                } else {
                    // Even length: average of max left and min right
                    return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
                }
            }
            // ❌ Step 4: Adjust search range
            else if (l1 > r2) {
                // Move left in a
                high = mid1 - 1;
            } else {
                // Move right in a
                low = mid1 + 1;
            }
        }

        return 0.0; // This line will never be reached for valid inputs
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median: " + findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median: " + findMedianSortedArrays(nums3, nums4)); // Output: 2.5

        int[] nums5 = {0, 0};
        int[] nums6 = {0, 0};
        System.out.println("Median: " + findMedianSortedArrays(nums5, nums6)); // Output: 0.0
    }
}
