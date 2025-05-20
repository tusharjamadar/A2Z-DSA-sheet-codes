/*
✅ Problem:
Implement the **Insertion Sort** algorithm. The goal is to sort an array of integers in ascending order by repeatedly taking one element at a time and placing it in its correct position in the sorted part of the array.

🧠 Intuition:
Insertion sort works by taking an element from the unsorted part of the array and inserting it into the correct position in the sorted part of the array. This is done by shifting elements of the sorted part of the array that are larger than the current element one position to the right, and then inserting the element at the appropriate position.

⚙️ Algorithm:
1. Start with the second element (since a single element is trivially sorted).
2. Compare it with the elements in the sorted portion (left side of the array).
3. Move the element to its correct position by shifting the larger elements.
4. Repeat the process for each subsequent element.
5. For the recursive version, the process continues with the remaining unsorted elements.

⏱ Time Complexity:
- **Best case**: O(n) when the array is already sorted, as no shifting is required.
- **Average and Worst case**: O(n^2), as we might need to compare each element with all other elements before inserting it in the correct position.

📦 Space Complexity:
- O(1), as insertion sort works in place, requiring no extra space besides the input array.

*/

import java.util.Arrays;

public class InsertionSort {

    // Recursive version of Insertion Sort
    private static void recInsertionSortFun(int[] arr, int i, int n) {
        if (i == n) 
            return; // Base case: when all elements have been sorted.

        // Insert arr[i] into the sorted part of the array (arr[0..i-1])
        for (int j = i; j > 0; j--) {
            if (arr[j - 1] > arr[j]) {
                // Swap if the current element is smaller than the previous one
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            } else {
                break; // No need to move further if the element is already in place
            }
        }

        // Recursive call to sort the next element
        recInsertionSortFun(arr, i + 1, n);
    }

    // Iterative version of Insertion Sort
    private static void insertionSortFun(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            // Move arr[i] to its correct position in the sorted part of the array
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    // Swap if the current element is smaller than the previous one
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break; // No need to move further if the element is already in place
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        // Print before sorting
        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        // Call the recursive insertion sort method
        recInsertionSortFun(arr, 1, arr.length);

        // Print after sorting
        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
