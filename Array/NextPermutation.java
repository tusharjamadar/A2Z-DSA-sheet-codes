import java.util.Arrays;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int ind1 = -1, ind2 = -1;

        // step 1 find breaking point
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind1 = i;
                break;
            }
        }
        // if there is no breaking point
        if (ind1 == -1) {
            reverse(nums, 0);
        }

        else {
            // step 2 find next greater element and swap with ind2
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] > nums[ind1]) {
                    ind2 = i;
                    break;
                }
            }

            swap(nums, ind1, ind2);
            // step 3 reverse the rest right half
            reverse(nums, ind1 + 1);
        }

    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;

        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3 };

        nextPermutation(arr);

        System.out.print(Arrays.toString(arr));
    }

}
