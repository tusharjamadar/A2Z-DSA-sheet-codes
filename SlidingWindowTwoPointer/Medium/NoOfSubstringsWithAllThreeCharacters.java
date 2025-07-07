package SlidingWindowTwoPointer.Medium;
/*
1358. Number of Substrings Containing All Three Characters

🧩 Problem:
Given a string `s` consisting only of characters 'a', 'b', and 'c',
return the number of substrings that contain at least one 'a', one 'b', and one 'c'.

🧠 Intuition:
We want to count how many substrings ending at index `i` have all 3 characters.
We use a simple array of size 3 to track the **last index** where 'a', 'b', and 'c' appeared.

👉 At each character `i`:
   - We update the latest index for s.charAt(i)
   - If all three characters have appeared so far (i.e., all values in hash are != -1),
     then the minimum of the three indices tells us how far back we can go
     while still ensuring the substring includes all three.

   - So we add `1 + min(hash[0], hash[1], hash[2])` to our count.

📌 Dry Run:
s = "abcabc"

i=0: hash[a]=0 → not all present → skip  
i=1: hash[b]=1 → not all present → skip  
i=2: hash[c]=2 → all present → min = 0 → count += (1 + 0) = 1  
i=3: hash[a]=3 → min=1 → count += (1 + 1) = 2 → total=3  
i=4: hash[b]=4 → min=2 → count += 3 → total=6  
i=5: hash[c]=5 → min=3 → count += 4 → total=10 ✅

📦 Space Complexity: O(1) – fixed array of 3  
⏱ Time Complexity: O(n) – one pass through the string
*/

class NoOfSubstringsWithAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        // Store last seen indices of 'a', 'b', and 'c'
        int[] hash = new int[]{-1, -1, -1};

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hash[ch - 'a'] = i;

            // Once all characters are seen at least once
            if (hash[0] != -1 && hash[1] != -1 && hash[2] != -1) {
                count += 1 + Math.min(hash[0], Math.min(hash[1], hash[2]));
            }
        }
        return count;
    }

    // 🔍 Main method to test the solution
    public static void main(String[] args) {
        NoOfSubstringsWithAllThreeCharacters sol = new NoOfSubstringsWithAllThreeCharacters();

        String s1 = "abcabc";
        System.out.println("Output 1: " + sol.numberOfSubstrings(s1)); // Expected: 10

        String s2 = "aaacb";
        System.out.println("Output 2: " + sol.numberOfSubstrings(s2)); // Expected: 3

        String s3 = "abc";
        System.out.println("Output 3: " + sol.numberOfSubstrings(s3)); // Expected: 1

        String s4 = "aaabbccabc";
        System.out.println("Output 4: " + sol.numberOfSubstrings(s4)); // Try more cases
    }
}
