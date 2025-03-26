package JavaCollections.CollectionOverview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * 
 * Iterable Interface is root interface for entire collection framework. Collecection interface extends the Iterable interface.
 * 
 * Iterable interface provides the iterator() method, which implements the Iterator interface.
 * 
 * Iterator interface provides three functions that are used to iterate through collection and also remove elements from collection.
 * 1. boolean hasNext()
 * 2. E next()
 * 3. void remove() : call once after next method. as multiple calls can result in unspecified behaviour in java.
 */

public class IteratorDemo {

    public static void removeEven(Collection<Integer> c) {
        Iterator<Integer> it = c.iterator();

        while (it.hasNext()) {
            int x = (Integer) it.next();

            if (x % 2 == 0)
                it.remove();
        }
    }

    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<>();

        c.add(10);
        c.add(15);
        c.add(20);

        removeEven(c);
        System.out.println(c);
    }
}
