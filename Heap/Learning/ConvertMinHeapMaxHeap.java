package Heap.Learning;
import java.util.Arrays;

/**
 * Problem:
 * Convert a given Min Heap (array form) into a Max Heap.
 *
 * Intuition:
 * A Min Heap ensures every parent <= children.
 * A Max Heap ensures every parent >= children.
 *
 * To convert, start from the last non-leaf node and apply max-heapify.
 * This is a bottom-up approach and ensures the heap property is restored efficiently.
 *
 * Dry Run:
 * Input: [1, 2, 3, 4]
 * Tree:
 *      1
 *    /   \
 *   2     3
 *  /
 * 4
 *
 * After conversion:
 *      4
 *    /   \
 *   2     3
 *  /
 * 1
 * Output: [4, 2, 3, 1]
 *
 * Algorithm:
 * - For all non-leaf nodes (from N/2 - 1 to 0), apply heapify()
 * - heapify ensures current node is larger than its children
 *
 * Time Complexity: O(N)
 * Space Complexity: O(1) (in-place)
 */

public class ConvertMinHeapMaxHeap {

    // Converts given min heap into max heap in-place
    static void convertMinToMaxHeap(int N, int[] arr) {
        // Start from last non-leaf node and heapify each
        for (int i = (N - 2) / 2; i >= 0; i--) {
            heapify(arr, N, i);
        }
    }

    // Maintains max heap property for node at index i
    static void heapify(int[] arr, int n, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Swaps elements at index i and j
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Test the conversion
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {3, 4, 8, 11, 13};

        convertMinToMaxHeap(arr1.length, arr1);
        convertMinToMaxHeap(arr2.length, arr2);

        System.out.println("Converted Max Heap 1: " + Arrays.toString(arr1)); // [4, 2, 3, 1]
        System.out.println("Converted Max Heap 2: " + Arrays.toString(arr2)); // [13, 11, 8, 3, 4]
    }
}
