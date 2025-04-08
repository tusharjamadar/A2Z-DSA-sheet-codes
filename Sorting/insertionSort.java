import java.util.Arrays;

/*
 * 1. Take element and place it to it's correct position
 */

public class insertionSort {
    private static void recInsertionSortFun(int[] arr, int i, int n) {

        if (i == n)
            return;

        for (int j = i - 1; j > 0; j--) {
            if (arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }
        }

        recInsertionSortFun(arr, i + 1, n);

    }

    private static void insertionSortFun(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        // insertionSortFun(arr);
        recInsertionSortFun(arr, 0, arr.length);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
