package JavaCollections.CollectionOverview;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * 1. containsAll(Collection<T> c) -> boolean
 * 2. addAll(Collection<? extends> c)
 * 3. removeAll(Collection<? extends> c) 
 * 4. retainAll(Collection<? extends> c) 
 * 5. retainAll(Collection<? extends> c)  
 * 6. removeIf(Predicate<? super E> filter)  
 */

public class BulkOperations {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();

        list1.add(10);
        list1.add(15);
        list1.add(30);

        List<Integer> list2 = new ArrayList<>();

        list2.add(10);
        list2.add(30);

        System.out.println(list1.containsAll(list2));

        // list1.addAll(list2);
        // System.out.println(list1);

        // list1.removeAll(list2);
        // System.out.println(list1);

        // list1.retainAll(list2);
        // System.out.println(list1);

        list1.removeIf((n) -> (n % 2 == 0));

        System.out.println(list1);
    }
}
