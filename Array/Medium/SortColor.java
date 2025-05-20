package Array.Medium;

import java.util.*;

public class SortColor {
    private static void sortColor(int[] arr){
        int n = arr.length;
        int low = 0, mid = 0, high = n-1;

        /*
         * 0 -> [0... low-1]
         * 1 -> [low ... mid-1]
         * 2 -> [high ... n-1]
         * 
         * 
         * {mid ... high-1} --> unknown part
         * 
         */

         while(mid <= high){
            if(arr[mid] == 0){
                // swap arr[mid] to arr[low]
                // increment both by 1
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;

                mid++; low++;
            }else if(arr[mid] == 1){
                // just increment mid by one
                mid++;
            }else{
                // swap arr[mid] with arr[high]
                // decrement high by one
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;

                high--;
            }
         }
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 0, 1, 0, 0, 2, 1, 2, 2, 1, 0};

        // sort 0... then 1.. then 2
        
        // Dutch national flag algorithm
        sortColor(arr);

        System.out.println(Arrays.toString(arr));
    }
}
