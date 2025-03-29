package JavaCollections.ArrayList;

import java.util.*;

public class ListIteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        list.add(10);
        list.add(20);
        list.add(30);

        ListIterator<Integer> it = list.listIterator();

        // front traversal
        while (it.hasNext()) {
            it.add(5);
            it.next();
        }
        System.out.println(list);

        // backward traversal

        ListIterator<Integer> it2 = list.listIterator(list.size());

        while (it2.hasPrevious()) {
            int x = it2.previous();
            it2.set(x * 2);
        }

        System.out.println(list);

        // get nextIndex or previousIndex

        System.out.println(it.previousIndex());
        System.out.println(it.nextIndex());

    }
}
