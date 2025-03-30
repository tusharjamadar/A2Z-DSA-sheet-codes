package JavaCollections.StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {
    public static void main(String[] args) {
        Deque<Integer> dq = new LinkedList<Integer>();

        dq.offerFirst(10);
        dq.offerLast(15);
        System.out.println(dq.peekFirst());
        System.out.println(dq.peekLast());
    }
}
