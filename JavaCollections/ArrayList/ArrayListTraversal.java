package JavaCollections.ArrayList;

import java.util.*;

public class ArrayListTraversal {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        System.out.println("1. For loop");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("2. For each");
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("3. Iterator Method");

        Iterator it = list.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
