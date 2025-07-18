
public class CheckArraySortedAndRoatedOrNot {

    // brute force approch -> TC: O(n*n) & SC: O(n)

    public static boolean check1(int[] nums) {
        int n = nums.length;
        int[] checkSorted = new int[n];

        for (int r = 0; r < n; r++) {
            int currIndex = 0;

            // add non rotated elements into temp array
            for (int i = r; i < n; i++) {
                checkSorted[currIndex++] = nums[i];
            }

            // now add rotated elements into temp array
            for (int i = 0; i < r; i++) {
                checkSorted[currIndex++] = nums[i];
            }

            // check temp array is sorted or not
            boolean isSorted = true;

            for (int i = 0; i < n - 1; i++) {
                if (checkSorted[i] > checkSorted[i + 1]) {
                    isSorted = false;
                    break;
                }
            }

            if (isSorted)
                return true;

        }
        return false;
    }

    // using inversion count nums[i] < nums[i-1] --> TC: O(n) & SC: O(1)
    public static boolean check2(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return true;

        int inversionCount = 0;
        // For every pair, count the number of inversions.
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                inversionCount++;
                if (inversionCount > 1)
                    return false;
            }
        }
        // Also check between the last and the first element due to rotation
        if (nums[0] < nums[n - 1]) {
            inversionCount++;
        }

        return inversionCount <= 1;

    }

    public static void main(String[] args) {
        int[] arr = new int[] { 3, 4, 5, 1, 2 };

        boolean res = check2(arr);

        System.out.println(res);
    }
}
