package String.Medium;

class SumOfBeautyOfAllSubstrings {

    /*
    ✅ Problem: 1781. Sum of Beauty of All Substrings
    ✅ Problem Details:
    The beauty of a string is defined as the difference between the highest and lowest frequency of characters in that string (ignoring characters that are not present).
    Given a string `s`, return the sum of beauty values of all substrings of `s`.

    ✅ Intuition:
    - We want to iterate over all substrings.
    - For each substring, calculate the frequency of characters.
    - Use those frequencies to determine the max and min (non-zero) frequencies.
    - Sum up all (max - min) values.

    ✅ Algorithm:
    1. Use two nested loops to generate all substrings.
    2. Maintain a frequency array of size 26 to count characters for current substring.
    3. After each new character is added, update max and min frequency.
    4. Add (max - min) to the result.
    5. Return the total sum.

    ✅ Time & Space Complexity:
    - Time: O(n^2 * 26) => O(n^2), where n is the length of the string.
    - Space: O(26) => O(1), as the frequency array is of constant size.
    */

    public int beautySum(String s) {
        int n = s.length();
        int res = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                freq[ch - 'a']++;

                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        min = Math.min(min, freq[k]);
                        max = Math.max(max, freq[k]);
                    }
                }

                res += max - min;
            }
        }

        return res;
    }
    // ✅ Main method for testing
    public static void main(String[] args) {
        SumOfBeautyOfAllSubstrings sol = new SumOfBeautyOfAllSubstrings();

        String test1 = "aabcb";
        System.out.println("Input: " + test1);
        System.out.println("Output: " + sol.beautySum(test1)); // Expected: 5

        String test2 = "aabcbaa";
        System.out.println("Input: " + test2);
        System.out.println("Output: " + sol.beautySum(test2)); // Expected: 17

        String test3 = "aaaaa";
        System.out.println("Input: " + test3);
        System.out.println("Output: " + sol.beautySum(test3)); // Expected: 0
    }
}
