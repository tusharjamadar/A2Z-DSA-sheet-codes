import java.util.*;

public class TaskScheduler {

    /**
     * 🔍 Problem: Given a list of tasks and a cooldown `n`, return the least number
     * of units of times that the CPU will take to finish all tasks, with the constraint
     * that the same task must be separated by at least `n` time units.
     */

    // ---------------------------------------------------------
    // ✅ SOLUTION 1: MATH BASED (Optimal)
    // ---------------------------------------------------------

    /**
     * Intuition:
     * Most frequent task determines the basic structure:
     * Suppose 'A' occurs 3 times and n = 2, we want something like:
     * A _ _ A _ _ A
     *
     * Number of parts = (maxFreq - 1)
     * Each part is (n + 1) slots wide (including the current task)
     * Add the count of tasks with max frequency at the end.
     *
     * Time Complexity: O(N), where N is length of tasks
     * Space Complexity: O(1), fixed size array of 26
     */
    public int leastIntervalMath(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int maxCount = 0;
        for (int f : freq) {
            if (f == maxFreq) maxCount++;
        }

        int partCount = maxFreq - 1;
        int partLength = n + 1;
        int totalSlots = partCount * partLength + maxCount;

        return Math.max(tasks.length, totalSlots);
    }

    // ---------------------------------------------------------
    // ✅ SOLUTION 2: PRIORITY QUEUE BASED (Simulation)
    // ---------------------------------------------------------

    /**
     * Intuition:
     * Always try to execute the most frequent task available.
     * In each cycle of (n+1) time units, try to schedule as many different tasks as possible.
     *
     * Time Complexity: O(N log 26) = O(N), since we push/pop from priority queue with max 26 elements.
     * Space Complexity: O(26) = O(1)
     */
    public int leastIntervalPQ(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) pq.offer(f);
        }

        int time = 0;

        while (!pq.isEmpty()) {
            int cycle = n + 1;
            List<Integer> temp = new ArrayList<>();
            int taskCount = 0;

            while (cycle-- > 0 && !pq.isEmpty()) {
                int curr = pq.poll();
                if (curr > 1) temp.add(curr - 1);
                taskCount++;
            }

            for (int remaining : temp) {
                pq.offer(remaining);
            }

            // If queue is empty, then we can count only the tasks we processed
            time += pq.isEmpty() ? taskCount : n + 1;
        }

        return time;
    }

    // ---------------------------------------------------------
    // ✅ MAIN FUNCTION TO TEST BOTH METHODS
    // ---------------------------------------------------------

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();

        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;

        System.out.println("Using Math-based approach: " + ts.leastIntervalMath(tasks, n));  // Output: 8
        System.out.println("Using PriorityQueue approach: " + ts.leastIntervalPQ(tasks, n));  // Output: 8
    }
}
