package Heap.Learning;

import java.util.*;

/**
 * Problem: Implement a Min Heap data structure with the basic operations:
 *          - insert(val)
 *          - extractMin()
 *          - peek()
 *          - size(), isEmpty()
 *
 * Intuition:
 * A min-heap is a complete binary tree where the value at the root is the smallest.
 * Every node must be smaller than or equal to its children.
 * Internally, the heap is stored in an ArrayList, and relationships are managed using index calculations.
 *
 * For a node at index i:
 *      - Left child: 2*i + 1
 *      - Right child: 2*i + 2
 *      - Parent: (i - 1)/2
 *
 * insert: Add the element to the end and bubble it up to the correct position (heapify up).
 * extractMin: Remove the root, replace it with the last element, and bubble it down to restore heap property.
 *
 * Dry Run:
 * Input = [5, 2, 10, 3, 8]
 * Insertion:
 *  - Add 5 → [5]
 *  - Add 2 → [2, 5] (heapify up swaps 2 with 5)
 *  - Add 10 → [2, 5, 10]
 *  - Add 3 → [2, 3, 10, 5] (heapify up swaps 3 with 5)
 *  - Add 8 → [2, 3, 10, 5, 8]
 * Extraction:
 *  - Remove 2 → [3, 5, 10, 8] → after heapify down
 *  - Remove 3 → [5, 8, 10]
 *  - Remove 5 → [8, 10]
 *  - Remove 8 → [10]
 *  - Remove 10 → []
 *
 * Time Complexity:
 * - insert: O(log n)
 * - extractMin: O(log n)
 * - peek: O(1)
 * - isEmpty, size: O(1)
 *
 * Space Complexity: O(n) for storing n elements in the list
 */

public class MinHeapImplementation {
    private List<Integer> heap;

    public MinHeapImplementation() {
        heap = new ArrayList<>();
    }

    // Returns number of elements in heap
    public int size() {
        return heap.size();
    }

    // Checks if heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Returns the minimum element (root)
    public int peek() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    // Inserts a new element and maintains heap property
    public void insert(int val) {
        heap.add(val); // Step 1: Add at the end
        heapifyUp(heap.size() - 1); // Step 2: Restore heap
    }

    // Removes and returns the minimum element
    public int extractMin() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");

        int min = heap.get(0); // Save min value
        int last = heap.remove(heap.size() - 1); // Remove last element

        if (!heap.isEmpty()) {
            heap.set(0, last); // Move last to root
            heapifyDown(0); // Restore heap property
        }

        return min;
    }

    // Moves the element at index 'i' upwards until heap property is restored
    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) < heap.get(parent)) {
                swap(i, parent);
                i = parent;
            } else break;
        }
    }

    // Moves the element at index 'i' downwards until heap property is restored
    private void heapifyDown(int i) {
        int size = heap.size();

        while (i < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < size && heap.get(right) < heap.get(smallest)) smallest = right;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    // Swaps elements at index i and j
    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        MinHeapImplementation minHeap = new MinHeapImplementation();
        int[] nums = {5, 2, 10, 3, 8};

        for (int num : nums) {
            minHeap.insert(num);
        }

        System.out.println("Min Heap Elements (in sorted order):");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " "); // Output: 2 3 5 8 10
        }
    }
}
