
import java.util.*;

public class QueueDemo {
    public static void main(String[] args) {
        // Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> q = new LinkedList<>();

        q.offer(10);
        q.offer(20);
        q.offer(30);

        for (Integer ele : q) {
            System.out.print(ele + " ");
        }

        System.out.println();

        System.out.println(q.peek());
        System.out.println(q.poll());
        System.out.println(q.peek());
    }
}
