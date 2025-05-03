package BinarySearch.OnAnswers;

class KthElementTwoArrays {

    /*
    Problem: 
    Given two sorted arrays `a[]` and `b[]`, and an integer `k`, find the k-th smallest element 
    in the combined sorted array formed by merging both arrays.

    Intuition:
    We want to partition the arrays such that the first `k` elements of the merged array come from 
    a prefix of `a` and a prefix of `b`. The maximum of the left partition gives the k-th element.

    Approach:
    We apply binary search on the number of elements to pick from array `a`. 
    The rest (k - picked from a) must come from array `b`.

    Why low and high are initialized as:
    - low = max(0, k - n2): can't pick more than k elements total, so if b has many, a must have at least k - n2.
    - high = min(k, n1): can't pick more than n1 from a or more than k total.
    */

    public int kthElement(int a[], int b[], int k) {
        int n1 = a.length, n2 = b.length;

        // Always binary search on the smaller array to reduce time complexity
        if (n1 > n2) return kthElement(b, a, k);

        // Possible number of elements to take from array a
        int low = Math.max(0, k - n2);      // minimum elements from a
        int high = Math.min(k, n1);         // maximum elements from a

        while (low <= high) {
            int mid1 = (low + high) / 2;    // elements from a
            int mid2 = k - mid1;            // remaining elements from b

            // Handling edges: if 0 elements taken, use MIN, if end reached, use MAX
            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? b[mid2] : Integer.MAX_VALUE;

            // Valid partition found
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }
            // Too many from a, reduce
            else if (l1 > r2) {
                high = mid1 - 1;
            }
            // Too few from a, increase
            else {
                low = mid1 + 1;
            }
        }

        return 0; // Will not be reached due to constraints
    }

    // Test the function with a sample main method
    public static void main(String[] args) {
        KthElementTwoArrays sol = new KthElementTwoArrays();

        int[] a1 = {2, 3, 6, 7, 9};
        int[] b1 = {1, 4, 8, 10};
        int k1 = 5;
        System.out.println("K-th element is: " + sol.kthElement(a1, b1, k1)); // Output: 6

        int[] a2 = {100, 112, 256, 349, 770};
        int[] b2 = {72, 86, 113, 119, 265, 445, 892};
        int k2 = 7;
        System.out.println("K-th element is: " + sol.kthElement(a2, b2, k2)); // Output: 256
    }
}

