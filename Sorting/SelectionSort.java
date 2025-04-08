import java.util.*;

/*
 * 1. Pick Minimum vlaue from arr
 * 2. Swap next to it with last sorted index 
 * 3. Do this same till n-2 
 */
public class SelectionSort {

    private static int findMin(int[] arr, int start, int end) {
        int res = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[res]) {
                res = i;
            }
        }
        return res;
    }

    private static void selectionSortFun(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int minIndex = findMin(arr, i, n);
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        selectionSortFun(arr);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
