package JavaCollections.PriorityQueue;

import java.util.*;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // 1. Min heap demo
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(27);
        minHeap.add(12);
        minHeap.add(15);
        minHeap.add(1);

        System.out.println("1. Min heap demo");

        for (Integer i : minHeap) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(minHeap.peek());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.peek());

        // 2. Max heap demo
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.add(27);
        maxHeap.add(12);
        maxHeap.add(15);
        maxHeap.add(1);

        System.out.println("2. Max heap demo");

        for (Integer i : maxHeap) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(maxHeap.peek());
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.peek());
    }
}
