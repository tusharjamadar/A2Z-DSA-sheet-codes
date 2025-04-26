package BinarySearch.On1DArrays;
/*
Problem: Search in Rotated Sorted Array II (with duplicates allowed)

Intuition:
1. Perform a modified binary search.
2. If duplicates are found at low, mid, and high → shrink search space by moving low++ and high--.
3. Otherwise, check if the left half or right half is sorted and move accordingly.

Approach:
- Binary Search with small adjustment for duplicates.
- Time Complexity: O(log n) on average, O(n) in worst case (all elements same).
- Space Complexity: O(1)

Example: [2,5,6,0,0,1,2], target = 0
*/

class SearchInRotatedSorted2 {
    public static boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If element found at mid
            if (arr[mid] == target) return true;

            // If elements at low, mid, high are equal, skip duplicates
            if (arr[low] == arr[mid] && arr[high] == arr[mid]) {
                low++;
                high--;
                continue;
            }

            // Left half is sorted
            if (arr[low] <= arr[mid]) {
                // Check if target lies in left half
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else { // Otherwise, move to right half
                    low = mid + 1;
                }
            } else { // Right half is sorted
                // Check if target lies in right half
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else { // Otherwise, move to left half
                    high = mid - 1;
                }
            }
        }

        // Target not found
        return false;
    }
    public static void main(String[] args) {
        int[] nums1 = {2,5,6,0,0,1,2};
        int target1 = 0;
        System.out.println(search(nums1, target1)); // Output: true

        int[] nums2 = {2,5,6,0,0,1,2};
        int target2 = 3;
        System.out.println(search(nums2, target2)); // Output: false

        int[] nums3 = {1,0,1,1,1};
        int target3 = 0;
        System.out.println(search(nums3, target3)); // Output: true
    }
}

