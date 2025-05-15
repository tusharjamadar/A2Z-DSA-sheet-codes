package SubsequencesPattern;


class SubsequenceWithSumK {
    private static boolean solve(int i, int sum, int[] arr, int n, int k){
        if(i == n){
            if(sum == k)return true;
            
            return false;
        }   
        
        if(sum > k)return false;
        
        sum += arr[i];
        if(solve(i+1, sum, arr, n, k) == true)return true;
        
        sum -= arr[i];
        
        if(solve(i+1, sum, arr, n, k) == true)return true;
        
        return false;
    }
    
    public static boolean checkSubsequenceSum(int N, int[] arr, int K) {
        // code here
        return solve(0, 0, arr, N, K);
    }
}
