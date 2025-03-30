package JavaCollections.HashSetAndLinkedHashSet;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<>();

        ts.add(1);
        ts.add(13);
        ts.add(5);
        ts.add(10);
        ts.add(19);

        for (Integer x : ts) {
            System.out.print(x + " ");
        }

        System.out.println();

        System.out.println(ts.ceiling(11));
        System.out.println(ts.floor(4));
        System.out.println(ts.lower(10));
        System.out.println(ts.higher(13));
    }
}
