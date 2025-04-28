package BinarySearch.OnAnswers;
/*
Problem: 1539. Kth Missing Positive Number

Given a sorted array 'arr' of positive integers, find the kth missing positive number.

Intuition:
- If there were no missing numbers, the value at index i would be (i + 1).
- So, the number of missing numbers until arr[i] is: arr[i] - (i + 1).
- We can use Binary Search to find the smallest index where missing numbers become ≥ k.

Approach:
1. Binary Search:
   - low = 0, high = arr.length - 1
   - For each mid, calculate missing = arr[mid] - (mid + 1)
   - If missing < k, move right (low = mid + 1)
   - Else, move left (high = mid - 1)
2. After search:
   - The answer is: (high + 1) + k

Time Complexity: O(log n)  
Space Complexity: O(1)

*/

class KthMissingPositiveNumber {

    public static int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // After binary search, low is the correct index
        return low + k;
    }

    // Main function to test locally
    public static void main(String[] args) {
        int[] arr1 = {2,3,4,7,11};
        System.out.println(findKthPositive(arr1, 5)); // Output: 9

        int[] arr2 = {1,2,3,4};
        System.out.println(findKthPositive(arr2, 2)); // Output: 6

        int[] arr3 = {5,6,7,8,9};
        System.out.println(findKthPositive(arr3, 1)); // Output: 1
    }
}
