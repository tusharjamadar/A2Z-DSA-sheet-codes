package JavaCollections.ArrayList;

import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2, 10);
        list.add(2);

        System.out.println(list.contains(2));

        list.remove(2);

        System.out.println(list.get(2));
        System.out.println(list.indexOf(2));
        System.out.println(list.lastIndexOf(2));
        System.out.println(list.indexOf(50));
        System.out.println(list);
    }
}
