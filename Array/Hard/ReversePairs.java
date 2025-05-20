/*
✅ Problem:
You are given an array. Find the number of reverse pairs (i, j) such that:
    - i < j
    - arr[i] > 2 * arr[j]

This is a variation of the classic "Count Inversions" problem.

🧠 Intuition:
Brute-force will take O(n^2) by checking all pairs. We can do better using a **modified merge sort** approach.
While merging, if `arr[i] > 2 * arr[j]`, then all elements from current `i` to `mid` will form valid reverse pairs with current `j`.

⚙️ Algorithm:
1. Use merge sort to divide the array.
2. While merging, count reverse pairs:
    - Use two pointers to find how many `arr[i] > 2 * arr[j]`.
3. Merge the arrays in sorted order.

⏱ Time Complexity:
- O(n log n): due to merge sort.
- O(n) for counting reverse pairs during merge.
=> Total: O(n log n)

📦 Space Complexity:
- O(n) for temporary merging arrays.
*/

package Array.Hard;

import java.util.ArrayList;

public class ReversePairs {

    // ✅ Helper: Merge two sorted halves and sort the array
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

        while (left <= mid) temp.add(arr[left++]);
        while (right <= high) temp.add(arr[right++]);

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    // ✅ Helper: Count reverse pairs where arr[i] > 2 * arr[j]
    public static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1, cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && arr[i] > 2L * arr[right]) right++;
            cnt += (right - (mid + 1));
        }
        return cnt;
    }

    // ✅ Modified merge sort
    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;

        int mid = (low + high) / 2;
        cnt += mergeSort(arr, low, mid);
        cnt += mergeSort(arr, mid + 1, high);
        cnt += countPairs(arr, low, mid, high);
        merge(arr, low, mid, high);
        return cnt;
    }

    // ✅ Main function to call
    public static int team(int[] skill, int n) {
        return mergeSort(skill, 0, n - 1);
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 2, 3, 1};
        int n = a.length;
        int cnt = team(a, n);
        System.out.println("The number of reverse pairs is: " + cnt);
    }
}
