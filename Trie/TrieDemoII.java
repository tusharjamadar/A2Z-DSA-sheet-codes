package Trie;

/**
 * ✅ Problem: Implement Trie with Extra Operations
 * ------------------------------------------------
 * Along with basic insert/search, we need to support:
 *   1. insert(word)                → Insert a word into the Trie.
 *   2. countWordsEqualTo(word)     → Return how many times a word has been inserted.
 *   3. countWordsStartingWith(pre) → Return count of words starting with given prefix.
 *   4. erase(word)                 → Remove one occurrence of a word from the Trie.
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Brute Force Approach (Explanation Only, Not Implemented):
 * -------------------------------------------------------------------------------------------
 * - Use an ArrayList<String> to store all inserted words.
 * - insert(word): Simply add to the list → O(1).
 * - countWordsEqualTo(word): Iterate list and count matches → O(N * L).
 * - countWordsStartingWith(prefix): Iterate all words, check prefix → O(N * L).
 * - erase(word): Remove one occurrence from list → O(N).
 *
 * Drawback: Too slow for large N (up to 30,000 operations as per constraints).
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Optimized Approach: Trie with Counters
 * -------------------------------------------------------------------------------------------
 * Intuition:
 * - Extend Trie nodes with counters:
 *   - cntPrefix → how many words pass through this node.
 *   - cntEndWith → how many words end at this node.
 *
 * Steps:
 *   1. insert(word):
 *      - Traverse chars; if missing → create new node.
 *      - Increment cntPrefix for each visited node.
 *      - After last char, increment cntEndWith.
 *
 *   2. countWordsEqualTo(word):
 *      - Traverse chars; if missing → return 0.
 *      - At the end, return cntEndWith.
 *
 *   3. countWordsStartingWith(prefix):
 *      - Traverse chars; if missing → return 0.
 *      - At the end, return cntPrefix.
 *
 *   4. erase(word):
 *      - Traverse chars; decrement cntPrefix along the way.
 *      - At the last char, decrement cntEndWith.
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Time Complexity:
 * -------------------------------------------------------------------------------------------
 * Let L = length of word/prefix.
 * - insert(word): O(L)
 * - countWordsEqualTo(word): O(L)
 * - countWordsStartingWith(prefix): O(L)
 * - erase(word): O(L)
 *
 * ✅ Space Complexity:
 * - Each node stores 26 children + 2 counters.
 * - In worst case: O(26 * N * L), but typically much less due to prefix sharing.
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Dry Run Example:
 * -------------------------------------------------------------------------------------------
 * Operations:
 *   trie.insert("apple");
 *   trie.insert("apple");
 *   trie.countWordsEqualTo("apple") → 2
 *   trie.countWordsStartingWith("app") → 2
 *
 *   trie.insert("app");
 *   trie.insert("app");
 *   trie.countWordsEqualTo("app") → 2
 *   trie.countWordsStartingWith("app") → 4
 *
 *   trie.erase("app");
 *   trie.countWordsEqualTo("app") → 1
 *   trie.countWordsStartingWith("app") → 3
 *
 *   trie.erase("apple");
 *   trie.countWordsEqualTo("apple") → 1
 *   trie.countWordsStartingWith("apple") → 1
 */

public class TrieDemoII {

    // ----------------------------- Node Class -----------------------------
    static class Node {
        Node[] links = new Node[26]; // children for each character
        int cntEndWith = 0;          // how many words end here
        int cntPrefix = 0;           // how many words pass through here

        boolean isContainsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        void increaseEnd() {
            cntEndWith++;
        }

        void increasePrefix() {
            cntPrefix++;
        }

        void deleteEnd() {
            cntEndWith--;
        }

        void decreasePrefix() {
            cntPrefix--;
        }
    }

    // ----------------------------- Trie Class -----------------------------
    private Node root;

    TrieDemoII() {
        root = new Node();
    }

    // Insert a word into the Trie
    void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.isContainsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
            node.increasePrefix(); // one more word passes through this node
        }
        node.increaseEnd(); // word ends here
    }

    // Count how many times a word was inserted
    int countWordsEqualTo(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.isContainsKey(ch)) {
                return 0; // word not found
            }
            node = node.get(ch);
        }
        return node.cntEndWith;
    }

    // Count words starting with a given prefix
    int countWordsStartingWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.isContainsKey(ch)) {
                return 0;
            }
            node = node.get(ch);
        }
        return node.cntPrefix;
    }

    // Erase one occurrence of a word from the Trie
    void erase(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.isContainsKey(ch)) {
                return; // word not found, nothing to erase
            }
            node = node.get(ch);
            node.decreasePrefix(); // reduce prefix count
        }
        node.deleteEnd(); // reduce end count
    }

    // ----------------------------- Main Method (Testing) -----------------------------
    public static void main(String[] args) {
        TrieDemoII trie = new TrieDemoII();

        System.out.println("Inserting 'apple' twice...");
        trie.insert("apple");
        trie.insert("apple");

        System.out.println("countWordsEqualTo('apple'): " + trie.countWordsEqualTo("apple")); // 2
        System.out.println("countWordsStartingWith('app'): " + trie.countWordsStartingWith("app")); // 2

        System.out.println("\nInserting 'app' twice...");
        trie.insert("app");
        trie.insert("app");

        System.out.println("countWordsEqualTo('app'): " + trie.countWordsEqualTo("app")); // 2
        System.out.println("countWordsStartingWith('app'): " + trie.countWordsStartingWith("app")); // 4

        System.out.println("\nErasing 'app' once...");
        trie.erase("app");
        System.out.println("countWordsEqualTo('app'): " + trie.countWordsEqualTo("app")); // 1
        System.out.println("countWordsStartingWith('app'): " + trie.countWordsStartingWith("app")); // 3

        System.out.println("\nErasing 'app' again...");
        trie.erase("app");
        System.out.println("countWordsEqualTo('app'): " + trie.countWordsEqualTo("app")); // 0
        System.out.println("countWordsStartingWith('app'): " + trie.countWordsStartingWith("app")); // 2

        System.out.println("\nErasing 'apple' once...");
        trie.erase("apple");
        System.out.println("countWordsEqualTo('apple'): " + trie.countWordsEqualTo("apple")); // 1
        System.out.println("countWordsStartingWith('apple'): " + trie.countWordsStartingWith("apple")); // 1
    }
}
