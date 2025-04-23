public class RemoveDuplicateElements {

    // Using two pointers i and j where j keep track of unique elements and i
    // travers array
    // TC -> O(n) , SC -> O(1)
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 1;

        int i = 1, j = 1;

        while (i < n) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
            i++;
        }

        return j;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 0, 0, 1, 1, 2, 2, 2, 2, 4, 4, 4, 5, 5, 5 };

        int k = removeDuplicates(arr);

        for (int i = 0; i < k; i++)
            System.out.print(arr[i] + " ");
    }
}
