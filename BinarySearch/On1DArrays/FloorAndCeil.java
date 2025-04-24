package BinarySearch.On1DArrays;

/*
Problem 1: Floor in a Sorted Array
Given a sorted array, find the index of the largest element ≤ x (i.e., floor of x).
Return the last occurrence in case of duplicates.
If no such element exists, return -1.

Problem 2: Ceil in a Sorted Array
Given a sorted array, find the index of the smallest element ≥ x (i.e., ceil of x).
Return the first occurrence in case of duplicates.
If no such element exists, return -1.

Approach for Both:
Use Binary Search:
- For Floor:
    → If mid element ≤ x, it's a candidate. Search right for higher candidates.
- For Ceil:
    → If mid element ≥ x, it's a candidate. Search left for earlier candidates.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class FloorAndCeil {

    // Finds floor index (last element ≤ x)
    public static int findFloor(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int res = -1; // -1 indicates floor doesn't exist

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= x) {
                res = mid;        // Valid candidate for floor
                low = mid + 1;    // Look for a higher candidate
            } else {
                high = mid - 1;
            }
        }

        return res;
    }

    // Finds ceil index (first element ≥ x)
    public static int findCeil(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int res = -1; // -1 indicates ceil doesn't exist

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                res = mid;        // Valid candidate for ceil
                high = mid - 1;   // Look for an earlier candidate
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    // Main method to test both floor and ceil functions
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 8, 10, 10, 12, 19};
        int[] arr2 = {1, 1, 2, 8, 10, 11, 12, 19};

        // Floor Tests
        System.out.println("Floor of 5 in arr1: " + findFloor(arr1, 5)); // Output: 1
        System.out.println("Floor of 11 in arr1: " + findFloor(arr1, 11)); // Output: 4
        System.out.println("Floor of 0 in arr1: " + findFloor(arr1, 0)); // Output: -1

        // Ceil Tests
        System.out.println("Ceil of 5 in arr1: " + findCeil(arr1, 5)); // Output: 2
        System.out.println("Ceil of 20 in arr1: " + findCeil(arr1, 20)); // Output: -1
        System.out.println("Ceil of 0 in arr2: " + findCeil(arr2, 0)); // Output: 0
    }
}
