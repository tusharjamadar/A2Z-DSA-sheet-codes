/*
✅ Problem:
Implement the **Selection Sort** algorithm. The goal is to sort an array of integers in ascending order by repeatedly finding the minimum element and placing it in the correct position.

🧠 Intuition:
Selection Sort works by selecting the smallest (or largest) element from the unsorted portion of the array and swapping it with the element at the beginning of the unsorted portion. The process continues until the entire array is sorted.

⚙️ Algorithm:
1. Iterate through the entire array.
2. For each iteration, find the minimum element in the unsorted part of the array.
3. Swap this minimum element with the first unsorted element.
4. Continue this until the array is sorted.

⏱ Time Complexity:
- **Best, Average, and Worst case**: O(n^2), because we have two nested loops (one for traversing the array and one for finding the minimum element).

📦 Space Complexity:
- O(1), as Selection Sort is an **in-place sorting algorithm**, requiring no additional space besides the input array.

*/

import java.util.Arrays;

public class SelectionSort {

    // Helper method to find the index of the minimum value in the unsorted portion of the array
    private static int findMin(int[] arr, int start, int end) {
        int res = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[res]) {
                res = i; // Update the index of the minimum value
            }
        }
        return res;
    }

    // Selection Sort function that sorts the array in ascending order
    private static void selectionSortFun(int[] arr) {
        int n = arr.length;

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Find the index of the minimum element in the unsorted part of the array
            int minIndex = findMin(arr, i, n);

            // Swap the minimum element found with the element at the current index
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // Main function to test the Selection Sort
    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 23, 56, 210 };

        // Print the array before sorting
        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        // Perform the selection sort
        selectionSortFun(arr);

        // Print the array after sorting
        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
