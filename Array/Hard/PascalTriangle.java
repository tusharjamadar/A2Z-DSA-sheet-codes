import java.util.*;

public class PascalTriangle {
    /*
     * find nCr in optimal way
     * There are three types of problem on based on given.
     * 1. Given row number and col number and print that perticular element
     * 2. Given row number and print all elements of row.
     * 3. Print all Pascal Triangle
     */

     // TC -> O(n)
     public static long findnCr(int n, int r){
        long res = 1;
        for(int i=0; i<r; i++){
            res = res * (n-i);
            res = res / (i+1);
        }

        return res;
     }

     // TC -> O(n * r)
     public static void printNthRow1(int n){

        for(int c=1; c<=n; c++){
            long res = findnCr(n-1, c-1);
            System.out.print(res+" ");
        }
     }

     // TC -> O(n)
     public static List<Integer> printNthRow2(int n){
        List<Integer> ans = new ArrayList<>();

        int res = 1;
        ans.add(res);

        for(int i=1; i<n; i++){
            res *= (n-i);
            res /= i;
            ans.add(res);
        }

        return ans;
     }

     public static List<List<Integer>> getPascalTraingle(int n) {
        List<List<Integer>> res = new ArrayList<>();

        for(int i=1; i<=n; i++){
            res.add(printNthRow2(i));
        }

        return res;
     }

    public static void main(String[] args) {
        int n = 6, r = 3;

        /*
         * Type 1 -> Print perticular element
         * Could be directly solve using (n-1)C(r-1) formula -> (n! / r! * (n-r)!)
         * Shortcut formula -> last r digit starting from n / r !
         * 
         * Suppose n = 10 and r = 3
         * 10C3 = 10 * 9 * 8 / 3 * 2 * 1
         * 
         * 10/1 * 9/2 * 8/3
         * 
         */

        long ele = findnCr(n - 1, r - 1);
        System.out.println(ele);

        /* 
         * Type 2 -> Print entire nth row
         */

        //  printNthRow2(n);

         /*
          * Type 3 -> Print entire pascal triangle
          */

          List<List<Integer>> output = getPascalTraingle(n);

          System.out.println(output);
    }
}
