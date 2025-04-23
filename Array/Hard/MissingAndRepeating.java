package Array;

import java.util.ArrayList;

public class MissingAndRepeating {
    public static ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        int originalSum = 0;
        int arraySum = 0;
        
        int missing = 0, repeated = 0;
        
        for(int i=0; i<arr.length; i++){
            int originalEle = Math.abs(arr[i]);
            int index = originalEle - 1;
            
            if(arr[index] < 0){
                repeated = originalEle;
            }
            
            arr[index] = -arr[index];
            originalSum += i+1;
            arraySum += originalEle;
        }
        
        missing = originalSum - (arraySum - repeated);
        
        result.add(repeated);
        result.add(missing);
        
        return result;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 2, 5, 4, 6, 7, 5};
        ArrayList<Integer> ans = findTwoElement(a);
        System.out.println("The repeating and missing numbers are: {"
                           + ans.get(0) + ", " + ans.get(1) + "}");
    }
}
