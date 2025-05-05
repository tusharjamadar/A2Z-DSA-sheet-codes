package String.Medium;
/*
Problem: 451. Sort Characters By Frequency

Problem Details:
Given a string `s`, sort it in decreasing order based on the frequency of characters.
Return any valid answer. Same characters must be grouped together.

Examples:
Input: "tree"      → Output: "eert" or "eetr"
Input: "cccaaa"    → Output: "aaaccc" or "cccaaa"
Input: "Aabb"      → Output: "bbAa" or "bbaA" (A and a are different)

Constraints:
1 <= s.length <= 5 * 10^5
s consists of uppercase/lowercase letters and digits.

Intuition:
- We need to count how frequently each character occurs.
- Then, sort characters by decreasing frequency.
- To keep characters grouped and sorted, we can use a max heap (priority queue).

Algorithm:
1. Traverse the string and build a frequency map for each character.
2. Insert map entries into a priority queue (max heap) based on frequency.
3. Build the result by polling the heap and appending each character its frequency number of times.

Time Complexity: O(n log k), where n is string length and k is number of unique characters  
Space Complexity: O(n + k) for frequency map, priority queue, and output
*/

import java.util.*;

class SortCharactersByFrequency {
    public String frequencySort(String s) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Use a max heap (priority queue) sorted by frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue() // Descending frequency
        );

        pq.addAll(freqMap.entrySet());

        // Step 3: Build the result
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            char ch = entry.getKey();
            int count = entry.getValue();
            sb.append(String.valueOf(ch).repeat(count)); // Append character count times
        }

        return sb.toString();
    }

    // Main method to test the function
    public static void main(String[] args) {
        SortCharactersByFrequency sol = new SortCharactersByFrequency();
        System.out.println(sol.frequencySort("tree"));    // eert or eetr
        System.out.println(sol.frequencySort("cccaaa"));  // cccaaa or aaaccc
        System.out.println(sol.frequencySort("Aabb"));    // bbAa or bbaA
    }
}

