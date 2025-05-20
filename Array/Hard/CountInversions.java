/*
🔢 Problem:
You are given an array of integers. You need to count the number of inversions in the array.
An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].

🧠 Intuition:
Brute-force would involve checking all pairs, but that would take O(n^2) time.
Instead, we can use a modified version of Merge Sort. While merging two sorted halves,
whenever an element from the right half is smaller than an element from the left half,
it means all the remaining elements in the left half will form inversions with this right-half element.

⚙️ Algorithm:
1. Perform Merge Sort on the array.
2. During the merge step, if arr[left] > arr[right], count the number of remaining elements in the left half (mid - left + 1).
3. Accumulate these counts during all merge steps.

⏱️ Time Complexity:
- O(n log n), where n = number of elements in the array (due to Merge Sort)

📦 Space Complexity:
- O(n) auxiliary space for the temporary array during merging.
*/

package Array.Hard;

import java.util.ArrayList;

public class CountInversions {

    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // Temporary array for merging
        int left = low;       // Starting index of left half
        int right = mid + 1;  // Starting index of right half
        int cnt = 0;          // Count of inversions

        // Merge step with inversion count
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
                cnt += (mid - left + 1); // All remaining elements in left half are > arr[right]
            }
        }

        // Add remaining elements of left half
        while (left <= mid) {
            temp.add(arr[left++]);
        }

        // Add remaining elements of right half
        while (right <= high) {
            temp.add(arr[right++]);
        }

        // Copy merged elements back to original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }

        return cnt;
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low < high) {
            int mid = (low + high) / 2;
            cnt += mergeSort(arr, low, mid);      // Count in left half
            cnt += mergeSort(arr, mid + 1, high); // Count in right half
            cnt += merge(arr, low, mid, high);    // Count during merge
        }
        return cnt;
    }

    public static int numberOfInversions(int[] a, int n) {
        return mergeSort(a, 0, n - 1);
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1};
        int n = a.length;
        int cnt = numberOfInversions(a, n);
        System.out.println("The number of inversions are: " + cnt); // Output: 10
    }
}
