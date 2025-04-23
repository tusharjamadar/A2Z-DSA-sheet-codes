import java.util.Arrays;

public class RearrangeBySign {

    public static int[] rearrangeArray(int[] nums) {

        // Use pointers for posIndex and negIndex
        int pos = 0, neg = 1;
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            // if positive element found then res[posIndex] = +ve element and update
            // posIndex
            if (nums[i] > 0) {
                res[pos] = nums[i];
                pos += 2;
            }
            // else negative element found then res[negIndex] = -ve element and update
            // negIndex
            else {
                res[neg] = nums[i];
                neg += 2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, -2, -5, 2, -4 };

        int[] res = rearrangeArray(arr);

        System.out.print(Arrays.toString(res));
    }
}
