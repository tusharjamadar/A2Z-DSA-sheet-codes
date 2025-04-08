package Array;

public class StockBuySell {
    private static int getMaxProfit(int[] arr){

        // 1. We will keep track of minimum ele on left of current index and will substract that mini from current ele 
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
            mini = Math.min(mini, arr[i]);
        }

        return maxProfit;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{7,1,5,3,6,4};

        // find maximum profit if we buy stock on one day and sell it on another next days.
      
        System.out.println(getMaxProfit(arr));
    }
}
