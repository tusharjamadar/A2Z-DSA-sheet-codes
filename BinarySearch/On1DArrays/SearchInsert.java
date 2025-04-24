package BinarySearch.On1DArrays;

/*
Problem: Search Insert Position

Given a sorted array of distinct integers and a target value, return the index if found. 
Otherwise, return the index where it would be if inserted in order.

Intuition:
Since the array is sorted, we can use Binary Search to achieve O(log n) time.
Track the lowest possible index (`res`) where the target could be inserted.

Approach:
1. Use binary search to look for the target.
2. If found, return its index.
3. If target is less than mid, update potential result `res` to mid and search left.
4. If target is greater than mid, search right.
5. If not found, return `res`, the position to insert.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

class SearchInsert {
    public static int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        // Binary search
        while (low <= high) {
            int mid = low + (high - low) / 2; // Safe mid calculation

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // If not found, 'low' is the insert position
        return low;
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        
        System.out.println("Insert position of 5: " + searchInsert(nums, 5)); // Output: 2
        System.out.println("Insert position of 2: " + searchInsert(nums, 2)); // Output: 1
        System.out.println("Insert position of 7: " + searchInsert(nums, 7)); // Output: 4
        System.out.println("Insert position of 0: " + searchInsert(nums, 0)); // Output: 0
    }
}

