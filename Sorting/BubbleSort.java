import java.util.Arrays;

/*
 * 1. check adj elements if they are not sort then swap them 
 * 2. at last we will get maximum element at last
 * 3. loop will run like 0-n-1, 0-n-2, 0-n-3, 0-n-4, ....
 */

public class BubbleSort {

    private static void recBubbleSortFun(int[] arr, int n) {
        if (n == 1)
            return;

        int didSwap = 0;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
                didSwap = 1;
            }
        }
        if (didSwap == 0)
            return;

        recBubbleSortFun(arr, n - 1);
    }

    private static void bubbleSortFun(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean isSwap = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    isSwap = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (isSwap == false)
                break;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        // bubbleSortFun(arr);

        recBubbleSortFun(arr, arr.length);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
