package JavaCollections.HashSetAndLinkedHashSet;

import java.util.LinkedHashSet;

public class LinkedSetDemo {
    public static void main(String[] args) {
        LinkedHashSet<Integer> s = new LinkedHashSet<>();

        s.add(20);
        s.add(30);
        s.add(49);

        s.remove(20);

        s.add(20);

        for (Integer x : s) {
            System.out.println(x);
        }

    }
}
