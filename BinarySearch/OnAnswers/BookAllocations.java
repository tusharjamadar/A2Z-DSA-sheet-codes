package BinarySearch.OnAnswers;

/*
Problem: Allocate Minimum Pages (Binary Search on Answer)

You are given a list of books with pages and a number of students.
The goal is to assign contiguous books to each student such that:
1. Each student gets at least one book.
2. Books are assigned contiguously.
3. No book is assigned to more than one student.
4. The maximum pages assigned to any student is minimized.

Approach:
- Use Binary Search on Answer (between max book pages and total sum of pages).
- For each `mid` (max pages per student), check if it’s possible to divide books among students.
- If possible → try for smaller max pages.
- If not possible → increase the allowed max pages.

Edge Case:
- If number of students > number of books → impossible to allocate.

Time Complexity: O(N log(S)) where:
- N = number of books
- S = sum of all pages - max of single book (search range)

Space Complexity: O(1)
*/

class BookAllocations {

    private static boolean isPossible(int[] arr, int maxPages, int k) {
        int count = 1, pages = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxPages) return false;

            if (pages + arr[i] > maxPages) {
                count++;
                pages = arr[i];
            } else {
                pages += arr[i];
            }
        }

        return count <= k;
    }

    public static int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1;

        int low = arr[0], high = 0;
        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }

        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(arr, mid, k)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    // Test code
    public static void main(String[] args) {
        System.out.println(findPages(new int[]{12, 34, 67, 90}, 2));  // Output: 113
        System.out.println(findPages(new int[]{15, 17, 20}, 5));      // Output: -1
        System.out.println(findPages(new int[]{22, 23, 67}, 1));      // Output: 112
    }
}

