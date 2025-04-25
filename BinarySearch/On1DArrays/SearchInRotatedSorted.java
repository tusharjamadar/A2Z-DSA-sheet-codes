package BinarySearch.On1DArrays;
/*
Problem 33: Search in Rotated Sorted Array

You are given a rotated sorted array and a target.
The goal is to find the index of the target using O(log n) time.

Example:
Input: [4,5,6,7,0,1,2], target = 0 → Output: 4

Intuition:
Even though the array is rotated, one of the two halves (left or right of mid) is always sorted.
Using binary search, we identify the sorted half and check if the target lies within that range.

Approach:
1. Start with binary search logic.
2. If middle element equals target, return index.
3. If left half is sorted:
    → Check if target lies within the left half, adjust search range accordingly.
4. If right half is sorted:
    → Check if target lies within the right half, adjust search range accordingly.
5. If target is not found, return -1.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class SearchInRotatedSorted {

    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Target found
            if (arr[mid] == target) return mid;

            // Check if left half is sorted
            if (arr[low] <= arr[mid]) {
                // Target is in left half
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else { // Target is in right half
                    low = mid + 1;
                }
            } else { // Right half is sorted
                // Target is in right half
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else { // Target is in left half
                    high = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums2 = {1};

        System.out.println("Index of 0: " + search(nums1, 0)); // Output: 4
        System.out.println("Index of 3: " + search(nums1, 3)); // Output: -1
        System.out.println("Index of 0 in single element array: " + search(nums2, 0)); // Output: -1
    }
}
