package JavaCollections.CollectionOverview;

import java.util.Arrays;
import java.util.List;

/*
 * Iterating through Collection
 * -> Iterators
 * -> for-each Loop
 * -> forEach Method
 * -> Stream
 */

public class IteratingMethod {
    public static void main(String[] args) {
        List<Integer> al = Arrays.asList(10, 20, 30, 40);
        // 1. for-each loop
        for (Integer x : al) {
            System.out.print(x + " ");
        }

        System.err.println();

        // 2. forEach method
        al.forEach(x -> System.out.print(x + " "));

        System.err.println();

        // 3. Stream
        List<Integer> list = Arrays.asList(10, 15, 7, 20, 40);

        list.stream().filter(x -> x > 10).filter(x -> x % 2 == 0).forEach(x -> System.out.print(x + " "));
    }
}
