package Array;

/*
Problem: Maximum Product Subarray

Intuition:
Multiplying negatives can turn into a positive, so we need to track both prefix and suffix products
(from left to right and right to left). Reset product to 1 when 0 is encountered. The max product 
can be anywhere, so we compare max of prefix, suffix, and current max at each step.

Approach:
1. Initialize variables to track the maximum product seen so far (`maxi`), prefix product (`pref`), and suffix product (`suff`).
2. Traverse the array from both ends simultaneously.
3. Reset prefix/suffix to 1 if it becomes 0 (as multiplying by 0 resets the product).
4. Update prefix with left-to-right product, suffix with right-to-left product.
5. Keep track of the maximum among prefix, suffix, and current maximum.
6. Return the maximum product found.

Time Complexity: O(n)
Space Complexity: O(1)
*/

class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        
        // Initialize maximum product to the smallest integer value
        int maxi = Integer.MIN_VALUE;

        // Prefix and Suffix product initialized to 1
        int pref = 1, suff = 1;

        // Loop through the array from both ends
        for(int i = 0; i < n; i++) {
            // If prefix becomes 0, reset to 1 to start new subarray
            if(pref == 0) pref = 1;
            
            // If suffix becomes 0, reset to 1 to start new subarray from end
            if(suff == 0) suff = 1;

            // Multiply prefix with the current element from start
            pref *= nums[i];
            
            // Multiply suffix with the current element from end
            suff *= nums[n - i - 1];

            // Update maximum product found so far
            maxi = Math.max(maxi, Math.max(pref, suff));
        }

        // Return the maximum product subarray value
        return maxi;
    }

    // Main method to test the function
    public static void main(String[] args) {
        MaximumProductSubarray sol = new MaximumProductSubarray();

        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Max product of nums1: " + sol.maxProduct(nums1)); // Output: 6

        int[] nums2 = {-2, 0, -1};
        System.out.println("Max product of nums2: " + sol.maxProduct(nums2)); // Output: 0

        int[] nums3 = {-2, 3, -4};
        System.out.println("Max product of nums3: " + sol.maxProduct(nums3)); // Output: 24
    }
}
