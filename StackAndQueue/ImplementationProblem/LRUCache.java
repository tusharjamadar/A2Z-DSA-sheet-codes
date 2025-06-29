import java.util.*;

/*
 * ✅ Problem:
 * Implement a data structure for Least Recently Used (LRU) Cache with:
 * - get(key): return the value if key exists, else -1
 * - put(key, value): update value or insert key-value; evict LRU if over capacity
 *
 * Both operations must run in average O(1) time.
 */

/*
 * 💡 Intuition:
 * We need O(1) access for get and O(1) insertion/removal for put.
 * -> Use a HashMap for O(1) access to nodes
 * -> Use Doubly Linked List to maintain order of use (most recent to least)
 *    - Move accessed node to front
 *    - Remove node from end if capacity exceeded
 *
 * 🧠 Algorithm:
 * - get(key):
 *     - If key exists in map:
 *         - Move node to front (mark as most recently used)
 *         - Return value
 *     - Else return -1
 * - put(key, value):
 *     - If key exists: update value, move to front
 *     - Else:
 *         - If over capacity, remove node from end (least recently used)
 *         - Insert new node at front
 *         - Add to map
 *
 * ⏱️ Time Complexity: O(1) for both get and put
 * 🗃️ Space Complexity: O(capacity)
 */

// Doubly Linked List Node
class Node {
    int key, val;
    Node prev, next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    private final Map<Integer, Node> map;
    private final int capacity;
    private final Node head; // dummy head
    private final Node tail; // dummy tail

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Initialize dummy head and tail
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        
        head.next = tail;
        tail.prev = head;
    }

    // 📌 GET operation
    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);

        // Move the accessed node to the front (MRU)
        remove(node);
        insertToFront(node);

        return node.val;
    }

    // 📌 PUT operation
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;

            // Move updated node to the front
            remove(node);
            insertToFront(node);
        } else {
            // Evict LRU if over capacity
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            // Insert new node at the front
            Node newNode = new Node(key, value);
            insertToFront(newNode);
            map.put(key, newNode);
        }
    }

    // 🧹 Helper: Remove node from list
    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // 📥 Helper: Insert node at the front (right after dummy head)
    private void insertToFront(Node node) {
        Node nextHead = head.next;

        node.prev = head;
        node.next = nextHead;

        head.next = node;
        nextHead.prev = node;
    }
}

/*
 * 🧪 Example usage:
 * LRUCache cache = new LRUCache(2);
 * cache.put(1, 1); // cache = {1=1}
 * cache.put(2, 2); // cache = {1=1, 2=2}
 * cache.get(1);    // returns 1, now {2=2, 1=1}
 * cache.put(3, 3); // evicts key 2, cache = {1=1, 3=3}
 * cache.get(2);    // returns -1 (not found)
 * cache.put(4, 4); // evicts key 1, cache = {3=3, 4=4}
 * cache.get(1);    // returns -1
 * cache.get(3);    // returns 3
 * cache.get(4);    // returns 4
 */
