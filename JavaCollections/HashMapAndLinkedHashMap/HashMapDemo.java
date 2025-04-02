

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, Integer> mp = new HashMap<>();

        // insertion order is maintained here
        // LinkedHashMap<Integer, Integer> mp = new LinkedHashMap<>();

        // Keys are sorted here with HigherKey+LowerKey+FloorKey+CeilingKey:
        // TreeMap<Integer, Integer> mp = new TreeMap<>();

        mp.put(10, 100);
        mp.put(11, 110);

        System.out.println(mp);
        System.out.println(mp.size());

        for (Map.Entry<Integer, Integer> e : mp.entrySet()) {
            System.out.println("{ " + e.getKey() + "-->" + e.getValue() + " } ");
        }

        System.out.println(mp.containsKey(10));
        System.out.println(mp.containsKey(-11));
        System.out.println(mp.containsValue(100));
    }
}
