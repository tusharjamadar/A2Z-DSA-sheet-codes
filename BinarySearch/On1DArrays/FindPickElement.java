package BinarySearch.On1DArrays;

/*
Problem: Find Peak Element

Intuition:
- A peak element is strictly greater than its neighbors.
- Since nums[-1] and nums[n] are -∞, boundary elements can also be peaks.
- Use binary search to achieve O(log n) time.

Approach:
- Check if middle element is a peak.
- If nums[mid] < nums[mid+1], move right (peak lies towards right).
- Else move left.

Time Complexity: O(log n)
Space Complexity: O(1)

Example: [1,2,1,3,5,6,4] → Output: 5 (element 6)
*/

class FindPickElement {

    public static int findPeakElement(int[] nums) {
        int n = nums.length;

        // Edge cases: Single element or peak at the boundaries
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Check if mid is peak
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // If mid is ascending, move right
            else if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            }
            // If mid is descending, move left
            else {
                high = mid - 1;
            }
        }

        // Should not reach here if input guarantees a peak
        return -1;
    }

    // Main method for quick testing
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(findPeakElement(nums1)); // Output: 2

        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums2)); // Output: 5 or 1 (both are valid)

        int[] nums3 = {1};
        System.out.println(findPeakElement(nums3)); // Output: 0

        int[] nums4 = {5, 4, 3, 2, 1};
        System.out.println(findPeakElement(nums4)); // Output: 0
    }
}
