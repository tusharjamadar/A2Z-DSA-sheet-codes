package String.Hard;

public class RabinKarpAlgo {
    // A large prime MOD value to avoid overflow and reduce hash collisions
    static int MOD = (int) 1e9 + 7;

    /**
     * ---------------------------------------------------------------------
     * Function: hashValue
     * ---------------------------------------------------------------------
     * Intuition:
     *  - Convert a string of length `m` into a numeric hash using polynomial rolling hash.
     *  - Formula: H = s[0]*RADIX^(m-1) + s[1]*RADIX^(m-2) + ... + s[m-1]*RADIX^0
     *  - RADIX is the base (26 for lowercase letters).
     *  - Take modulo MOD to avoid overflow and compress values.
     *
     * Why from right to left?
     *  - We start with factor = 1 (for lowest power).
     *  - Multiply factor by RADIX each time to build higher powers as we move left.
     */
    public long hashValue(String str, long RADIX, int m){
        long ans = 0, factor = 1;

        // Traverse substring from rightmost char to leftmost
        for(int i = m-1; i >= 0; i--){
            // Map character to a number in [0..25]
            int charVal = str.charAt(i) - 'a';

            // Add current character contribution: charVal * factor
            ans += (charVal * factor) % MOD;

            // Move to higher power of RADIX
            factor = (factor * RADIX) % MOD;
        }

        // Return final hash modulo MOD
        return ans;
    }

    /**
     * ---------------------------------------------------------------------
     * Function: rabinKarp
     * ---------------------------------------------------------------------
     * Intuition:
     *  - Sliding window of size m = pattern.length()
     *  - Compute initial hash of first window in str and hash of pattern.
     *  - For each next window:
     *      * Shift previous hash using rolling formula.
     *      * Compare with pattern hash.
     *      * If equal, do character-by-character verification (to handle collisions).
     *
     * Rolling Hash Update Formula:
     *   newHash = (oldHash * RADIX - outgoingChar * RADIX^m + incomingChar + MOD) % MOD
     *
     * Why this works:
     *   - Multiplying by RADIX shifts all powers up by one (like shifting digits).
     *   - Subtract outgoing char (multiplied by RADIX^m).
     *   - Add new incoming char as lowest order term.
     *   - "+ MOD" ensures non-negative result before modulo.
     */
    public int rabinKarp(String str, String pattern){
        int n = str.length(), m = pattern.length();

        // Edge case: pattern longer than string -> not possible
        if(n < m) return -1;
        
        long RADIX = 26, MAX_WEIGHT = 1;

        // Precompute RADIX^m % MOD
        // Used for subtracting outgoing character during rolling hash update
        for(long i = 1; i<=m; i++){
            MAX_WEIGHT = (MAX_WEIGHT * RADIX) % MOD;
        }

        // Compute hash of pattern once
        long patHash = hashValue(pattern, RADIX, m), strHash = 0;

        // Slide window of length m across string
        for(int i = 0; i <= n - m; i++){
            if(i==0){
                // First window: compute full hash from scratch
                strHash = hashValue(str, RADIX, m);
            }else{
                // Rolling hash update
                strHash = ((strHash * RADIX) % MOD                          // shift all chars
                          - ((str.charAt(i-1) - 'a') * MAX_WEIGHT) % MOD   // remove outgoing char
                          + (str.charAt(i + m -1) - 'a')                   // add incoming char
                          + MOD) % MOD;                                    // ensure positive
            }

            // If hash matches, verify characters to avoid false positive
            if(strHash == patHash){
                for(int j=0; j<m; j++){
                    if(pattern.charAt(j) != str.charAt(j + i))break;

                    // If all characters match -> return index i
                    if(j == m-1)return i;
                }
            }
        }

        // If no match found
        return -1;
    }

    /**
     * ---------------------------------------------------------------------
     * main()
     * ---------------------------------------------------------------------
     * Testing with different cases
     *
     * Dry Run Example:
     *   str = "tusharjamadar", pattern = "arj"
     *   - pattern length m = 3
     *   - patHash = hash("arj")
     *   - Compute hash of "tus", "ush", "sha", ... until match
     *   - At window "arj" starting at index 4 -> hash matches, chars verified
     *   - Answer = 4
     */
    public static void main(String[] args) {
        RabinKarpAlgo sol = new RabinKarpAlgo();

        String str1 = "tusharjamadar", pat1 = "arj";
        System.out.println(sol.rabinKarp(str1, pat1)); // Expected: 4
    }
}

/**
 * ---------------------------------------------------------------------
 * Time Complexity:
 *   - Building hash: O(m)
 *   - Sliding windows: O(n-m+1)
 *   - Each update O(1), but verification O(m) only when hashes match
 *   - Average case: O(n + m)
 *   - Worst case (many collisions): O(n*m)
 *
 * Space Complexity:
 *   - O(1) extra (hashes, constants)
 *
 * ---------------------------------------------------------------------
 */



// public class RabinKarpAlgo {
//     static int MOD = (int) 1e9+7;

//     public long hashValue(String str, long RADIX, int m){
//         long ans = 0, factor = 1;

//         for(int i = m-1; i >= 0; i--){
//             ans += ((str.charAt(i) - 'a') * factor) % MOD;
//             factor = (factor * RADIX) % MOD;
//         }

//         return ans;
//     }

//     public int rabinKarp(String str, String pattern){
//         int n = str.length(), m = pattern.length();

//         if(n < m)return -1;
        
//         long RADIX = 26, MAX_WEIGHT = 1;

//         for(long i = 1; i<=m; i++){
//             MAX_WEIGHT = (MAX_WEIGHT * RADIX) % MOD;
//         }

//         long patHash = hashValue(pattern, RADIX, m), strHash = 0;

//         for(int i = 0; i <= n - m; i++){
//             if(i==0){
//                 strHash = hashValue(str, RADIX, m);
//             }else{
//                 strHash = ((strHash * RADIX) % MOD - ((str.charAt(i-1) - 'a') * MAX_WEIGHT) % MOD + (str.charAt(i + m -1) - 'a') + MOD) % MOD;
//             }

//             if(strHash == patHash){
//                 for(int j=0; j<m; j++){
//                     if(pattern.charAt(j) != str.charAt(j + i))break;
//                     if(j == m-1)return i;
//                 }
//             }
//         }


//         return -1;
//     }

//     public static void main(String[] args) {
//         RabinKarpAlgo sol = new RabinKarpAlgo();

//         String str1 = "tusharjamadar", pat1 = "arj";

//         System.out.println(sol.rabinKarp(str1, pat1));
        
//     }
// }
