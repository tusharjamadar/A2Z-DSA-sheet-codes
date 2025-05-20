/*
✅ Problem:
You are given an array of size N that contains numbers from 1 to N. 
One number is **missing** and one number is **repeated**. 
Your task is to find both numbers.

🧠 Intuition:
If we modify the array by marking indices visited (by negating values at the index), 
we can detect which number is repeated (because it leads to a second negation attempt at the same index).
Then, by calculating the sum difference, we can derive the missing number.

⚙️ Algorithm:
1. Iterate through the array:
   - For each number `x`, go to index `x-1`.
   - If the number at index `x-1` is already negative, then `x` is the repeated number.
   - Otherwise, mark it negative.
2. At the same time, keep track of:
   - Total sum from 1 to N
   - Total sum of the array values as we go
3. The missing number can be calculated as:
   `missing = originalSum - (arraySum - repeated)`

⏱ Time Complexity:
- O(N), where N is the size of the array

📦 Space Complexity:
- O(1), in-place modification of array (ignoring output list)
*/

package Array.Hard;

import java.util.ArrayList;

public class MissingAndRepeating {
    public static ArrayList<Integer> findTwoElement(int arr[]) {
        ArrayList<Integer> result = new ArrayList<>();
        int originalSum = 0;
        int arraySum = 0;

        int missing = 0, repeated = 0;

        for (int i = 0; i < arr.length; i++) {
            int originalEle = Math.abs(arr[i]);
            int index = originalEle - 1;

            // If already marked negative, this is the repeated element
            if (arr[index] < 0) {
                repeated = originalEle;
            }

            arr[index] = -arr[index]; // Mark as visited
            originalSum += (i + 1); // Sum from 1 to N
            arraySum += originalEle; // Actual sum in array
        }

        // Derive the missing number
        missing = originalSum - (arraySum - repeated);

        result.add(repeated);
        result.add(missing);

        return result;
    }

    public static void main(String[] args) {
        int[] a = { 3, 1, 2, 5, 4, 6, 7, 5 };
        ArrayList<Integer> ans = findTwoElement(a);
        System.out.println("The repeating and missing numbers are: {"
                + ans.get(0) + ", " + ans.get(1) + "}");
    }
}
