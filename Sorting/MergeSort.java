import java.util.ArrayList;
import java.util.Arrays;

/*
 * Divide and merge 
 * TC -> O(nlogn)
 * SC -> O(n)
 */

public class MergeSort {

    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
            }
        }

        while (left <= mid) {
            temp.add(arr[left++]);
        }
        while (right <= high) {
            temp.add(arr[right++]);
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    private static void mergeSortFun(int[] arr, int low, int high) {
        if (low >= high)
            return;

        int mid = (low + high) / 2;

        mergeSortFun(arr, low, mid);
        mergeSortFun(arr, mid + 1, high);

        merge(arr, low, mid, high);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        int n = arr.length;
        mergeSortFun(arr, 0, n - 1);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
