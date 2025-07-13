package Heap.Hard;
import java.util.*;

/**
 * Problem: Design a data structure that supports adding numbers from a stream
 * and finding the median in O(log N) time per operation.
 *
 * Intuition:
 * - Use two heaps:
 *   1. maxHeap for the left half (smaller numbers)
 *   2. minHeap for the right half (larger numbers)
 * - Keep both heaps balanced such that:
 *   - maxHeap may have at most 1 extra element (to handle odd-sized stream)
 * - This structure allows constant-time median retrieval.
 *
 * Time Complexity:
 * - addNum(): O(log N)
 * - findMedian(): O(1)
 *
 * Space Complexity:
 * - O(N), where N is the number of elements added
 */

class FindMedianFromStream {
    // maxHeap stores the smaller half of numbers
    PriorityQueue<Integer> maxHeap;
    
    // minHeap stores the larger half of numbers
    PriorityQueue<Integer> minHeap;

    /** Initialize the data structure */
    public FindMedianFromStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max-heap
        minHeap = new PriorityQueue<>(); // Min-heap
    }

    /**
     * Adds a number into the data structure
     * Logic:
     * - Add to maxHeap first (for left half)
     * - Move the largest from maxHeap to minHeap (to keep order)
     * - Rebalance: if minHeap has more elements, move one back to maxHeap
     */
    public void addNum(int num) {
        maxHeap.offer(num); // Step 1: insert to left half
        minHeap.offer(maxHeap.poll()); // Step 2: move max to right half

        // Step 3: maintain size property
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Returns the median of all numbers added so far
     * - If size is even, return average of tops of both heaps
     * - If odd, return top of maxHeap (since it has the extra element)
     */
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek(); // maxHeap has one more element
        }
    }

    // Main test driver
    public static void main(String[] args) {
        FindMedianFromStream medianFinder = new FindMedianFromStream();
        medianFinder.addNum(1);                 // arr = [1]
        medianFinder.addNum(2);                 // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // Output: 1.5
        medianFinder.addNum(3);                 // arr = [1, 2, 3]
        System.out.println(medianFinder.findMedian()); // Output: 2.0
    }
}
