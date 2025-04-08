import java.util.Arrays;

public class QuickSort {
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;

        while (i < j) {
            while (arr[i] <= pivot && i <= high - 1) {
                i++;
            }

            while (arr[j] > pivot && j >= low + 1) {
                j--;
            }

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[j];
        arr[j] = arr[low];
        arr[low] = temp;

        return j;
    }

    private static void quickSortFun(int[] arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high);
            quickSortFun(arr, low, pIndex - 1);
            quickSortFun(arr, pIndex + 1, high);

        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 1, 22, 55, 22, 122, 11, 435, 3, 23, 56, 210 };

        System.out.println("Before sorting");
        System.out.println(Arrays.toString(arr));

        int n = arr.length;
        quickSortFun(arr, 0, n - 1);

        System.out.println("After sorting");
        System.out.println(Arrays.toString(arr));
    }
}
