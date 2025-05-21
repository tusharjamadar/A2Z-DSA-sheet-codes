package StackAndQueue.Learning;

import java.util.*;

public class QueueUsingStack {

    static class Queue {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        // Enqueue an item to the queue
        void enqueue(int x) {

            // Push item into the first stack
            s1.push(x);
        }

        // Dequeue an item from the queue
        int dequeue() {

            // if both stacks are empty
            if (s1.empty() && s2.empty()) {
                return -1;
            }

            // if s2 is empty, move
            // elements from s1
            if (s2.empty()) {
                while (!s1.empty()) {
                    s2.push(s1.peek());
                    s1.pop();
                }
            }

            // return the top item from s2
            int x = s2.peek();
            s2.pop();
            return x;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}