package Heap.Learning;

import java.util.*;

/**
 * Problem:
 * Implement a Max Heap data structure that supports the following operations:
 * - insert(val)
 * - extractMax()
 * - peek()
 * - size(), isEmpty()
 *
 * Intuition:
 * A max-heap is a complete binary tree where the value at each node is greater than or equal to its children.
 * Internally, it's implemented using an ArrayList where:
 * - Left child: 2*i + 1
 * - Right child: 2*i + 2
 * - Parent: (i - 1) / 2
 *
 * Dry Run:
 * Insert [3, 10, 5, 2, 8] one by one:
 * - [3]
 * - [10, 3]          (10 moved up)
 * - [10, 3, 5]
 * - [10, 3, 5, 2]
 * - [10, 8, 5, 2, 3] (8 moved up)
 *
 * Extraction:
 * - Remove 10 → [8, 3, 5, 2]
 * - Remove 8  → [5, 3, 2]
 * - Remove 5  → [3, 2]
 * - Remove 3  → [2]
 * - Remove 2  → []
 *
 * Algorithm:
 * - insert(val): Add to end and bubble up until max-heap property is restored (heapifyUp)
 * - extractMax(): Remove root, replace with last element, and bubble down (heapifyDown)
 *
 * Time Complexity:
 * - insert: O(log n)
 * - extractMax: O(log n)
 * - peek: O(1)
 * - isEmpty, size: O(1)
 *
 * Space Complexity: O(n)
 */

public class MaxHeapImplementation {
    List<Integer> heap;

    // Constructor to initialize the heap
    public MaxHeapImplementation() {
        heap = new ArrayList<>();
    }

    // Returns the number of elements in the heap
    public int size() {
        return heap.size();
    }

    // Checks if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Returns the maximum element (root) without removing it
    public int peek() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    // Inserts a value into the heap and restores the max-heap property
    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    // Removes and returns the maximum element (root)
    public int extractMax() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");

        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return max;
    }

    // Restores the heap by moving the element at index 'i' upwards
    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) > heap.get(parent)) {
                swap(i, parent);
                i = parent;
            } else break;
        }
    }

    // Restores the heap by moving the element at index 'i' downwards
    private void heapifyDown(int i) {
        int size = heap.size();

        while (i < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < size && heap.get(left) > heap.get(largest)) largest = left;
            if (right < size && heap.get(right) > heap.get(largest)) largest = right;

            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else break;
        }
    }

    // Utility to swap elements at indices i and j
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Test function for the Max Heap
    public static void main(String[] args) {
        MaxHeapImplementation maxHeap = new MaxHeapImplementation();
        int[] nums = {3, 10, 5, 2, 8};

        for (int num : nums) {
            maxHeap.insert(num);
        }

        System.out.println("Extracted elements in max-heap order:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.extractMax() + " "); // Output: 10 8 5 3 2
        }
    }
}
