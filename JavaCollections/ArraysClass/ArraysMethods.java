

import java.util.*;

public class ArraysMethods {
    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 3, 4, 5 };
        int[] b = new int[] { 1, 2, 3, 4, 5, 6 };

        int[][] mat = new int[3][3];

        // System.out.println(Arrays.toString(a));

        // Arrays.fill(b, 1, 4, -1);
        // System.out.println(Arrays.toString(b));

        // for (int[] row : mat) {
        // Arrays.fill(row, -1);
        // }

        // for (int row[] : mat) {
        // System.out.println(Arrays.toString(row));
        // }

        // System.out.println(Arrays.binarySearch(a, 1, 2, 2));

        System.out.println(Arrays.equals(a, b));

        System.out.println(Arrays.mismatch(a, b));

        int res = Arrays.compare(a, b);

        if (res == 0) {
            System.out.println("Same");
        } else if (res > 0) {
            System.out.println("a is greater");
        } else {
            System.out.println("a is smaller");
        }
        Integer[] arr = new Integer[] { 1, 2, 2342, 4, 5 };
        List<Integer> list = Arrays.asList(arr);
        Collections.reverse(list);
        Collections.sort(list, Collections.reverseOrder());

        System.out.println(Arrays.toString(arr));
    }

}
