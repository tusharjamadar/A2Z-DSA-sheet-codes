import java.util.*;

public class HandOfStraights {

    /**
     * ✅ Function to check if cards can be divided into groups of consecutive values.
     *
     * 🔍 Intuition:
     * - We must repeatedly pick the smallest available card and try to build a group of size `groupSize`
     *   starting from it.
     * - A min-heap ensures we always start from the smallest card.
     * - A hashmap keeps track of how many cards of each value are left.
     *
     * 🧠 Dry Run:
     * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
     * Step 1: Sort and count -> {1:1, 2:2, 3:2, 4:1, 6:1, 7:1, 8:1}
     * Step 2: Use PQ to always start from the lowest card -> try building [1,2,3], [2,3,4], [6,7,8]
     * All can be built → return true.
     *
     * ⏱ Time Complexity: O(N log N), where N = hand.length (due to sorting via priority queue)
     * 🧠 Space Complexity: O(N) for HashMap and PQ
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Map<Integer, Integer> countMap = new HashMap<>();

        // Count frequencies of each card
        for (int card : hand) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }

        // Use a min-heap to always start with the smallest number
        PriorityQueue<Integer> pq = new PriorityQueue<>(countMap.keySet());

        // Try forming groups starting from the smallest card
        while (!pq.isEmpty()) {
            int start = pq.peek(); // Start of the group

            // Build a group of 'groupSize' consecutive cards
            for (int i = 0; i < groupSize; i++) {
                int card = start + i;
                if (!countMap.containsKey(card)) return false;

                countMap.put(card, countMap.get(card) - 1);
                if (countMap.get(card) == 0) {
                    countMap.remove(card);
                    // Remove from heap only if it's the current min
                    if (!pq.isEmpty() && pq.peek() == card) {
                        pq.poll();
                    }
                }
            }
        }

        return true;
    }

    // ✅ MAIN FUNCTION FOR TESTING
    public static void main(String[] args) {
        HandOfStraights solution = new HandOfStraights();

        int[] hand1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize1 = 3;
        System.out.println("Example 1 → " + solution.isNStraightHand(hand1, groupSize1)); // true

        int[] hand2 = {1, 2, 3, 4, 5};
        int groupSize2 = 4;
        System.out.println("Example 2 → " + solution.isNStraightHand(hand2, groupSize2)); // false

        int[] hand3 = {8,10,12};
        int groupSize3 = 3;
        System.out.println("Example 3 → " + solution.isNStraightHand(hand3, groupSize3)); // false
    }
}
