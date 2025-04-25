// package Array;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    public static List<Integer> majorityElement(int[] nums) {
        // ========================[ BRUTE FORCE ]========================
        // Linear Search
        // int n = nums.length;
        // List<Integer> majorityElements = new ArrayList<>();

        // for (int i = 0; i < n; i++) {
        //     if (majorityElements.size() == 0 || majorityElements.get(0) != nums[i]) {
        //         int count = 0;
        //         for (int j = 0; j < n; j++) {
        //             if (nums[j] == nums[i]) {
        //                 count++;
        //             }
        //         }
        //         if (count > n / 3) {
        //             majorityElements.add(nums[i]);
        //         }
        //     }
        //     if (majorityElements.size() == 2) {
        //         break;
        //     }
        // }

        // return majorityElements;



        // ========================[ BETTER ]========================
        // Map
        // int n = nums.length;
        // Map<Integer, Integer> freqMap = new HashMap<>();
        // List<Integer> majorityElements = new ArrayList<>();

        // for(int i = 0; i < n; i++) {
        //     freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        // }

        // for(Integer key : freqMap.keySet()) {
        //     if(freqMap.get(key) > n/3) {
        //         majorityElements.add(key);
        //     }
        // }

        // return majorityElements;



        // ========================[ OPTIMAL ]========================
        if(nums == null) return new ArrayList<>();
        int n = nums.length, e1 = Integer.MIN_VALUE, c1 = 0, e2 = Integer.MIN_VALUE, c2 = 0;

        for(int i = 0; i < n; i++) {
            if(c1 == 0 && e2 != nums[i]) {
                e1 = nums[i];
                c1 = 1;
            }
            else if(c2 == 0 && e1 != nums[i]) {
                e2 = nums[i];
                c2 = 1;
            }
            else if(e1 == nums[i]) {
                c1++;
            }
            else if(e2 == nums[i]) {
                c2++;
            }
            else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;
        for(int i = 0; i < n; i++) {
            if(e1 == nums[i]) {
                c1++;
            }
            if(e2 == nums[i]) {
                c2++;
            }
        }

        List<Integer> majorityElements = new ArrayList<>();
        if(c1 > n/3) majorityElements.add(e1);
        if(c2 > n/3) majorityElements.add(e2);

        return majorityElements;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,7,7,8,8,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9,3};

        // All elements appears more than n/3 times
        System.out.println(majorityElement(arr));
    }
}
