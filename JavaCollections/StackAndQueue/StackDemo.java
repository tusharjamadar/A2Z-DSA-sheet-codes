
import java.util.*;

public class StackDemo {
    public static void main(String[] args) {
        // Stack is best suitable for multi thread env
        // Stack<Integer> st = new Stack<>();

        // ArrayDeque is best suitable for single thread env
        ArrayDeque<Integer> st = new ArrayDeque<>();

        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.isEmpty());
        System.out.println(st.size());

    }
}
