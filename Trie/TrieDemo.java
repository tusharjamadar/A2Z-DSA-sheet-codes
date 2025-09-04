package Trie;

/**
 * ✅ Problem: Implement Trie (Prefix Tree) [LeetCode 208]
 *
 * A Trie is a tree-based data structure used for efficient storage and retrieval of strings,
 * especially useful for prefix-based operations like autocomplete and spell checking.
 *
 * We need to implement three main operations:
 *   1. insert(word)     → Inserts a word into the trie.
 *   2. search(word)     → Returns true if the word exists in the trie.
 *   3. startsWith(prefix) → Returns true if any word in the trie starts with the given prefix.
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Brute Force Approach (NOT IMPLEMENTED, just explanation):
 * -------------------------------------------------------------------------------------------
 * - Store all inserted words in a List or HashSet.
 * - search(word): Simply check if the word exists in the List/Set → O(N * L)
 * - startsWith(prefix): Iterate through all words, check if any starts with prefix → O(N * L)
 *   where N = number of words, L = length of prefix/word.
 *
 * Drawback: Very inefficient for large inputs (up to 30,000 operations as per constraints).
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Optimized Approach: Trie (Prefix Tree)
 * -------------------------------------------------------------------------------------------
 * Intuition:
 * - Represent each word as a path in a tree.
 * - Each node stores references to its 26 children (for lowercase letters).
 * - A boolean flag marks the end of a word.
 *
 * Steps:
 * 1. Insert(word): Traverse character by character, create nodes if not present, and mark end.
 * 2. Search(word): Traverse character by character; if any char is missing → return false.
 *                  At the end, check if last node is marked as "end of word".
 * 3. StartsWith(prefix): Traverse characters; if any missing → return false; else return true.
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Time Complexity:
 * -------------------------------------------------------------------------------------------
 * Let L = length of word/prefix.
 * - insert(word): O(L)
 * - search(word): O(L)
 * - startsWith(prefix): O(L)
 *
 * ✅ Space Complexity:
 * - Each node has 26 possible children → O(26 * N * L) in worst case.
 * - But usually much less due to shared prefixes.
 *
 * -------------------------------------------------------------------------------------------
 * ✅ Dry Run Example:
 * -------------------------------------------------------------------------------------------
 * Operations:
 *   Trie trie = new Trie();
 *   trie.insert("apple");
 *   trie.search("apple");   // true
 *   trie.search("app");     // false
 *   trie.startsWith("app"); // true
 *   trie.insert("app");
 *   trie.search("app");     // true
 *
 * Step-by-step:
 *   insert("apple"): root → a → p → p → l → e (end marked)
 *   search("apple"): path exists & end marked → true
 *   search("app"): path exists but not end → false
 *   startsWith("app"): path exists → true
 *   insert("app"): mark "p" as end → now valid
 *   search("app"): path exists & end marked → true
 */

public class TrieDemo {

    // ----------------------------- Node Class -----------------------------
    static class Node {
        Node[] links = new Node[26]; // links for 26 lowercase letters
        boolean flag = false;        // marks end of word

        // check if this node contains a link for given character
        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        // put a node corresponding to a character
        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        // get node for a character
        Node get(char ch) {
            return links[ch - 'a'];
        }

        // mark this node as end of a word
        void setEnd() {
            flag = true;
        }

        // check if this node marks end of a word
        boolean isEnd() {
            return flag;
        }
    }

    // ----------------------------- Trie Class -----------------------------
    static class Trie { 
        private Node root;

        public Trie() {
            root = new Node();
        }

        // Insert a word into the trie
        public void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    node.put(ch, new Node()); // create node if missing
                }
                node = node.get(ch); // move to next node
            }
            node.setEnd(); // mark end of word
        }

        // Search if a word exists in the trie
        public boolean search(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) return false;
                node = node.get(ch);
            }
            return node.isEnd();
        }

        // Check if any word starts with given prefix
        public boolean startsWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                if (!node.containsKey(ch)) return false;
                node = node.get(ch);
            }
            return true;
        }
    }

    // ----------------------------- Main Method (Testing) -----------------------------
    public static void main(String[] args) {
        Trie trie = new Trie();

        System.out.println("Inserting 'apple'");
        trie.insert("apple");

        System.out.println("Search 'apple': " + trie.search("apple"));   // true
        System.out.println("Search 'app': " + trie.search("app"));       // false
        System.out.println("StartsWith 'app': " + trie.startsWith("app"));// true

        System.out.println("Inserting 'app'");
        trie.insert("app");

        System.out.println("Search 'app': " + trie.search("app"));       // true
    }
}

