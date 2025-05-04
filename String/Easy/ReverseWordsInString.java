package String.Easy;

/*
Problem: 151. Reverse Words in a String

Problem Details:
Given a string `s`, reverse the order of words. A word is defined as a sequence of non-space characters.
Return a string where:
1. Words are in reverse order.
2. Each word is separated by a single space.
3. There are no leading or trailing spaces, and multiple spaces between words are reduced to one.

Intuition:
- The problem is essentially about reversing word positions in the string while ensuring space formatting is corrected.
- We can use a character array and perform in-place reversal:
   1. Clean the string to remove extra spaces.
   2. Reverse each word individually.
   3. Reverse the entire cleaned string.

Algorithm:
1. Remove leading, trailing, and extra spaces using a helper function `cleanSpaces`.
2. Convert the cleaned string into a character array.
3. Reverse each word in place.
4. Reverse the entire array to get the final output.

Time Complexity:
O(n) where n = length of string.
- O(n) for cleaning spaces
- O(n) to reverse words and final reversal
*/

class ReverseWordsInString {
    // Reverses characters from index 's' to 'e' in array 'arr'
    public void reverse(char[] arr, int s, int e) {
        while (s < e) {
            char temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }

    // Helper method to trim and reduce multiple spaces to one
    private String cleanSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0, n = s.length();

        while (i < n) {
            // Skip leading spaces
            while (i < n && s.charAt(i) == ' ') i++;

            // Add word characters
            while (i < n && s.charAt(i) != ' ') {
                sb.append(s.charAt(i++));
            }

            // Skip extra spaces and add single space between words
            while (i < n && s.charAt(i) == ' ') i++;
            if (i < n) sb.append(' ');
        }

        return sb.toString();
    }

    // Main function to reverse words in the string
    public String reverseWords(String s) {
        s = cleanSpaces(s);                // Step 1: Clean up spaces
        int start = 0, end = 0;
        char[] arr = s.toCharArray();      // Step 2: Convert to char array
        int n = arr.length;

        while (end < n) {
            if (arr[end] == ' ') {
                reverse(arr, start, end - 1);  // Step 3: Reverse individual word
                end++;
                start = end;
            } else {
                end++;
            }
        }

        reverse(arr, start, n - 1);       // Reverse the last word
        reverse(arr, 0, n - 1);           // Step 4: Reverse the whole string

        return new String(arr);
    }

    // Main method to test the solution
    public static void main(String[] args) {
        ReverseWordsInString sol = new ReverseWordsInString();

        String s1 = "the sky is blue";
        String s2 = "  hello world  ";
        String s3 = "a good   example";

        System.out.println("Output 1: " + sol.reverseWords(s1)); // Expected: "blue is sky the"
        System.out.println("Output 2: " + sol.reverseWords(s2)); // Expected: "world hello"
        System.out.println("Output 3: " + sol.reverseWords(s3)); // Expected: "example good a"
    }
}
