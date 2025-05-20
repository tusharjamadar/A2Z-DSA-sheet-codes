package Array.Medium;

public class MaximumSubarraySum {
    private static int getMaxSum(int[] arr){
        int sum = 0, maxSum = Integer.MIN_VALUE;

        // Kadane's Algorithm
        // 1. -> Add current ele in sum
        // 2. -> check sum is greater than our maxSum then update it 
        // 3. -> if sum became negative then update the sum = 0 as negative number will always decrease our future result

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            maxSum = sum > maxSum ? sum : maxSum;

            if(sum < 0) sum = 0;
        }

        return maxSum;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        // find maximum subarray sum 
        
        // Solve using kadane's algorithm
        System.out.println(getMaxSum(arr));
    }
}
