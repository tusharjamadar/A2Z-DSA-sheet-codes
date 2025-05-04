package Array.Medium;

public class MajorityElement {
    private static int getMajority(int[] arr){

        int n = arr.length;

        // Two steps in Moore's voting algorithm
        // 1. Find possible majority element
        // 2. Varify it

        // Step 1 -> find majority element

        int cnt = 1, ele = arr[0];

        for(int i=1; i<n; i++){
            if(cnt == 0){
                ele = arr[i];
                cnt = 1;
            }else if(arr[i] == ele){
                cnt++;
            }else{
                cnt--;
            }
        }

        // Step 2 -> Varify majority element

        int checkCnt = 0;
        for(int i=0; i<n; i++){
            if(arr[i] == ele)checkCnt++;
        }
        if(checkCnt > (n/2))return ele;
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2,2,1,1,1,2,2};

        // find element who occurs more that n/2 
        
        // Solve using Moore's voting algorithm
        System.out.println(getMajority(arr));
    }
}
