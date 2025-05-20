package Array.Medium;

public class StockBuySell {
    private static int getMaxProfit(int[] arr){

        // 1. We will keep track of minimum ele on left of current index and will subtract that mini from current ele 
        // 2. if we get cost higher that maxProfit then update it 
        // 3. Also update the mini if we found minimum than mini

        // maxProfit will store maximum profit and mini will keep track of minimum ele on left of current index
        int maxProfit = 0, mini = arr[0];

        for(int i=0; i<arr.length; i++){
            // calculate the cost for current day
            int cost = arr[i] - mini;
            // update maxProfit 
            maxProfit = Math.max(maxProfit, cost);

            // update mini
            mini = Math.min(maxProfit, cost);

        }

        return maxProfit;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        // find maximum profit if we buy stock on one day and sell it on another next days.
      
        System.out.println(getMaxProfit(arr));
    }
}
