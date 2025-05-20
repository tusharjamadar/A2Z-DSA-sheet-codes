/*
✅ Problem:
Implement the **Bubble Sort** algorithm. The goal is to sort an array of integers in ascending order using the bubble sort technique.

🧠 Intuition:
Bubble sort works by repeatedly comparing adjacent elements of the array and swapping them if they are in the wrong order. After each pass through the array, the largest unsorted element will "bubble" up to its correct position. This process is repeated until no swaps are made, indicating that the array is sorted.

⚙️ Algorithm:
1. **Iterate** over the entire array, comparing each element with the next.
2. **Swap** the elements if they are in the wrong order.
3. After each pass, the largest unsorted element is placed at the end.
4. **Recursion (for Recursion version)**: The process is repeated recursively by reducing the size of the unsorted section.
5. **Early termination**: If no elements are swapped during a pass, the array is already sorted, and we can terminate early.

⏱ Time Complexity:
- **Best case**: O(n) when the array is already sorted (early termination).
- **Average and Worst case**: O(n^2), since for every element, we may need to compare it with all other elements in the array.

📦 Space Complexity:
- O(1), as we are using a constant amount of extra space (no additional data structures).

*/

import java.util.Arrays;

public class BubbleSort {

    // Recursive version of Bubble Sort
    private static void recBubbleSortFun(int[] arr, int n) {
        if (n == 1)
            return; // Base case: when array is reduced to one element, stop recursion.

        int didSwap = 0;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                // Swap if the current element is greater than the next
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
                didSwap = 1; // Mark that a swap occurred
            }
        }
        if (didSwap == 0)
            return; // If no swap occurred, the array is sorted.

        // Recur for the next smaller subarray (excluding the last sorted element)
        recBubbleSortFun(arr, n - 1);
    }

    // Iterative version of Bubble Sort
    private static void bubbleSortFun(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean isSwap = false;

            for (int j = 0; j < n - i - 1; j++) {
                // Compare and swap if elements are in the wrong order
                if (arr[j] > arr[j + 1]) {
                    isSwap = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            // If no swap was made, break out of the loop (early termination)
            if (!isSwap)
                break;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        // Print before sorting
        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        // Call the recursive bubble sort method
        recBubbleSortFun(arr, arr.length);

        // Print after sorting
        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
