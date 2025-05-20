/*
🔢 Problem:
Given an array `a` and an integer `k`, count the number of subarrays whose XOR is equal to `k`.

🧠 Intuition:
Let `prefixXor[i]` be the XOR of all elements from index 0 to i.
Now, for a subarray (l to r) to have XOR equal to `k`, the condition is:
    prefixXor[r] ^ prefixXor[l-1] = k
Rearranged:
    prefixXor[l-1] = prefixXor[r] ^ k

So we keep track of all prefix XORs seen so far and count how many times the required prefix XOR (prefixXor[r] ^ k) has occurred.

⚙️ Algorithm:
1. Initialize `xr = 0` (current prefix XOR).
2. Use a map to store frequencies of each prefix XOR.
3. For every element:
    - Update prefix XOR: `xr ^= a[i]`
    - Check how many times `(xr ^ k)` has appeared before and add it to count.
    - Update the frequency of the current prefix XOR in the map.

⏱️ Time Complexity:
- O(n), where n = size of the array

📦 Space Complexity:
- O(n) for the HashMap to store prefix XOR frequencies
*/

package Array.Hard;

import java.util.HashMap;
import java.util.Map;

public class CountSubarraysXOR {
    public static int subarraysWithXorK(int[] a, int k) {
        int n = a.length;
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>();
        mpp.put(xr, 1);  // Base case: XOR 0 has occurred once
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            xr ^= a[i];  // Compute prefix XOR

            // Find if there is a prefix XOR which when XORed with k gives current xr
            int x = xr ^ k;

            // If such prefix exists, add its frequency to count
            cnt += mpp.getOrDefault(x, 0);

            // Update frequency of current prefix XOR
            mpp.put(xr, mpp.getOrDefault(xr, 0) + 1);
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;
        int ans = subarraysWithXorK(a, k);
        System.out.println("The number of subarrays with XOR k is: " + ans); // Output: 4
    }
}
