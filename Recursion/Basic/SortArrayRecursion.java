package Basic;

import java.util.*;

public class SortArrayRecursion {
    public static void solve(ArrayList<Integer> arr) {
        // Base Condition
        if (arr.size() == 1) {
            return;
        }
        // Hypothesis
        int temp = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        solve(arr);

        // induction
        insert(arr, temp);
    }

    public static void insert(ArrayList<Integer> arr, int temp) {
        // base condition
        if (arr.size() == 0 || arr.get(arr.size() - 1) <= temp) {
            arr.add(temp);
            return;
        }

        // Hypothesis
        int x = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        insert(arr, temp);

        // induction
        arr.add(x);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(5, 2, 1, 6, 2, 6));

        solve(arr);

        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
