package BinarySearch.OnAnswers;

class SplitArrayLargestSum {

    // Helper function to count the number of subarrays/partitions 
    // required if no subarray has sum > maxSum.
    public static int countPartitions(int[] a, int maxSum) {
        int partitions = 1;
        long subarraySum = 0;
        
        for (int num : a) {
            if (subarraySum + num <= maxSum) {
                subarraySum += num;
            } else {
                partitions++;
                subarraySum = num;
            }
        }
        return partitions;
    }

    /*
    Problem 1: Split Array Largest Sum
    Problem 2: Painter's Partition Problem-II
    - Both require splitting array into k subarrays such that the max sum among them is minimized.
    - Binary Search on the answer space: [max of array, total sum]
    - Check feasibility using greedy countPartitions()
    */
    public int splitArray(int[] a, int k) {
        int low = 0, high = 0;
        
        for (int num : a) {
            low = Math.max(low, num); // max element (can't be smaller)
            high += num;              // total sum (upper bound)
        }

        // Binary search to minimize the largest sum of partitions
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int requiredPartitions = countPartitions(a, mid);
            
            if (requiredPartitions > k) {
                // Too many partitions → increase allowed maxSum
                low = mid + 1;
            } else {
                // Try smaller maxSum
                high = mid - 1;
            }
        }

        return low;
    }

    // Optional main method to test both scenarios
    public static void main(String[] args) {
        SplitArrayLargestSum sol = new SplitArrayLargestSum();

        // Test for Split Array Largest Sum
        System.out.println("Split Array Output: " + sol.splitArray(new int[]{7, 2, 5, 10, 8}, 2)); // Output: 18

        // Test for Painter's Partition Problem-II
        System.out.println("Painter Output: " + sol.splitArray(new int[]{5, 10, 30, 20, 15}, 3)); // Output: 35
    }
}
