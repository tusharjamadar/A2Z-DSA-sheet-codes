package BinarySearch.On1DArrays;
/*
Problem: Single Element in a Sorted Array

Intuition:
1. In a sorted array where every element appears twice except one, pairs will start at even indices.
2. Before the single element, pairs are perfectly placed. After the single element, the pattern shifts.
3. Use binary search to find the single element by checking neighbors.

Approach:
- If nums[mid] == nums[mid-1] or nums[mid+1], adjust search based on index parity.
- Time Complexity: O(log n)
- Space Complexity: O(1)

Example: [1,1,2,3,3,4,4,8,8] → Output: 2
*/

class SingleElementSortedArray {

    // Function to find the single non-duplicate element
    public static int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        // Edge cases: single element or unique element at start or end
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Unique element found
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            // Check the pattern
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                // Move to the right half
                low = mid + 1;
            } else {
                // Move to the left half
                high = mid - 1;
            }
        }

        // Should never reach here if input is valid
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums1 = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(nums1)); // Output: 2

        int[] nums2 = {3,3,7,7,10,11,11};
        System.out.println(singleNonDuplicate(nums2)); // Output: 10

        int[] nums3 = {5};
        System.out.println(singleNonDuplicate(nums3)); // Output: 5

        int[] nums4 = {1,1,2,2,3,3,4};
        System.out.println(singleNonDuplicate(nums4)); // Output: 4
    }
}
