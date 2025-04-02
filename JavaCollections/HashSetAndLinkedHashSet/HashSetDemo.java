

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<Integer> st = new HashSet<>();

        st.add(1);
        st.add(2);
        st.add(1);
        st.add(11);
        st.add(12);

        System.out.println(st);

        st.remove(1);

        for (Integer i : st) {
            System.out.println(i);
        }
        System.out.println(st.contains(1));
        System.out.println(st.isEmpty());

    }
}
