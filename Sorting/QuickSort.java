import java.util.Arrays;

/*
✅ Problem:
Implement the **Quick Sort** algorithm to sort an array using divide and conquer.

🧠 Intuition:
QuickSort selects a "pivot" and partitions the array such that:
- elements smaller than the pivot are on the left
- elements greater than the pivot are on the right
It then recursively sorts the left and right parts.

⚙️ Algorithm:
1. Choose the first element as the pivot.
2. Use two pointers (i, j) to find elements that are on the wrong side of the pivot and swap them.
3. Place the pivot in its correct sorted position.
4. Recursively apply QuickSort to left and right subarrays.

⏱ Time Complexity:
- Best & Average: O(n log n)
- Worst (when array is already sorted and bad pivot chosen): O(n^2)

📦 Space Complexity:
- O(log n) for recursion stack (average case)
*/

public class QuickSort {

    // Partition function places pivot at correct position
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // Choose the first element as pivot
        int i = low, j = high;

        while (i < j) {
            // Move i until you find an element greater than pivot
            while (i <= high - 1 && arr[i] <= pivot) {
                i++;
            }

            // Move j until you find an element smaller than or equal to pivot
            while (j >= low + 1 && arr[j] > pivot) {
                j--;
            }

            // Swap arr[i] and arr[j] if i < j
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot (arr[low]) with arr[j] to put pivot in correct position
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j; // Return the pivot index
    }

    // Recursive QuickSort function
    private static void quickSortFun(int[] arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high); // Partition the array
            quickSortFun(arr, low, pIndex - 1);     // Recursively sort left part
            quickSortFun(arr, pIndex + 1, high);    // Recursively sort right part
        }
    }

    // Driver method
    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        quickSortFun(arr, 0, arr.length - 1);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
