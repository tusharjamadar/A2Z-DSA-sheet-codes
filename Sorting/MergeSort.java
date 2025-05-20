import java.util.ArrayList;
import java.util.Arrays;

/*
✅ Problem:
Implement the **Merge Sort** algorithm. The goal is to sort an array in ascending order using the divide and conquer approach.

🧠 Intuition:
Merge Sort divides the array into halves recursively, sorts them, and then merges the sorted halves. This ensures the overall array is sorted.

⚙️ Algorithm:
1. Recursively divide the array into two halves until each part has a single element.
2. Merge the sorted halves back while sorting them during the merge.
3. Copy the sorted temporary list back to the original array.

⏱ Time Complexity:
- Best, Average, Worst: O(n log n)

📦 Space Complexity:
- O(n), for the temporary array used during merging.

*/

public class MergeSort {

    // Merges two sorted subarrays: arr[low...mid] and arr[mid+1...high]
    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;

        // Merge the two sorted halves
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
            }
        }

        // Copy remaining elements from left half (if any)
        while (left <= mid) {
            temp.add(arr[left++]);
        }

        // Copy remaining elements from right half (if any)
        while (right <= high) {
            temp.add(arr[right++]);
        }

        // Copy sorted elements back to original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    // Recursive Merge Sort function
    private static void mergeSortFun(int[] arr, int low, int high) {
        // Base case: when only one element
        if (low >= high)
            return;

        int mid = (low + high) / 2;

        // Recursively sort the left and right halves
        mergeSortFun(arr, low, mid);
        mergeSortFun(arr, mid + 1, high);

        // Merge the sorted halves
        merge(arr, low, mid, high);
    }

    // Main method to test the Merge Sort
    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        int n = arr.length;
        mergeSortFun(arr, 0, n - 1);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
