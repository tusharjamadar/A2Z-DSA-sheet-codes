package String.Easy;

/*
Problem: 205. Isomorphic Strings

Problem Details:
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All characters in s must map to characters in t one-to-one and consistently.

Examples:
Input: s = "egg", t = "add"  → Output: true
Input: s = "foo", t = "bar"  → Output: false
Input: s = "paper", t = "title" → Output: true

Constraints:
1 <= s.length <= 5 * 10^4
s.length == t.length
s and t consist of any valid ASCII character.

Intuition:
- Two strings are isomorphic if:
  - The mapping from characters in `s` to characters in `t` is consistent.
  - No two different characters in `s` map to the same character in `t`.
- We use two arrays to track the last seen position of each character in `s` and `t`.
- If characters from `s` and `t` are not synced in last occurrence, they are not isomorphic.

Algorithm:
1. Create two arrays `mapS` and `mapT` to store last seen indices for characters from `s` and `t`.
2. For each character at index `i`, check if their last seen positions match.
3. If not, return false.
4. Update their last seen position to `i + 1` (to differentiate from default 0).
5. If all positions match, return true.

Time Complexity: O(n), where n is the length of the strings  
Space Complexity: O(1), because array size is fixed (128 for ASCII characters)
*/

class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[128];  // Stores last seen positions for characters in s
        int[] mapT = new int[128];  // Stores last seen positions for characters in t

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            // If the last positions don't match, mapping is inconsistent
            if (mapS[sChar] != mapT[tChar]) {
                return false;
            }

            // Store the index+1 to differentiate from default value 0
            mapS[sChar] = i + 1;
            mapT[tChar] = i + 1;
        }

        return true;
    }

    // Main method to test the function
    public static void main(String[] args) {
        IsomorphicStrings sol = new IsomorphicStrings();

        System.out.println(sol.isIsomorphic("egg", "add"));     // true
        System.out.println(sol.isIsomorphic("foo", "bar"));     // false
        System.out.println(sol.isIsomorphic("paper", "title")); // true
        System.out.println(sol.isIsomorphic("ab", "aa"));       // false
    }
}

