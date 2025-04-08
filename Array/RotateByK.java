import java.util.Arrays;

public class RotateByK {
    private static void rev(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 1)
            return;

        // to handle index out of bound if k > n
        k %= n;

        // 1. reverse whole array 0 to n-1
        rev(nums, 0, n - 1);
        // 2. reverse first half from 0 to k-1
        rev(nums, 0, k - 1);
        // 2. reverse second half from k to n-1
        rev(nums, k, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int k = 3;

        rotate(arr, k);

        System.out.print(Arrays.toString(arr));
    }
}
