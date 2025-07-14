package Greedy.Medium;

import java.util.*;

/*
✅ Problem Statement:
----------------------
You are given start and end times of N meetings. Only one meeting can be scheduled at a time in one room.
Return the **maximum number of meetings** that can be held without any overlap.

🔸 Note: The end time of one meeting cannot be equal to the start time of another.

Example:
Input:
    start[] = [1, 3, 0, 5, 8, 5]
    end[]   = [2, 4, 6, 7, 9, 9]

Output: 4
Explanation: Meetings (1,2), (3,4), (5,7), and (8,9) can be attended.

----------------------
✅ Intuition:
This is a classic **Greedy Algorithm** problem:
- To maximize the number of non-overlapping meetings, always pick the meeting that finishes the earliest (i.e., sort by end time).
- Then, greedily select the next meeting whose start time is strictly **after** the last selected meeting’s end time.

----------------------
✅ Step-by-step Algorithm:
1. Pair each meeting's start and end times.
2. Sort the meetings by end time (to prioritize earlier finishing meetings).
3. Initialize `count = 1` (select the first meeting).
4. Iterate through remaining meetings:
   - If current meeting's start time > last selected meeting’s end time, select it and increment count.
5. Return the final count.

----------------------
✅ Time Complexity:
- O(n log n): For sorting the meetings based on end time.
- O(n): For iterating through meetings.

✅ Space Complexity:
- O(n): For storing start-end pairs of meetings.
*/

class N_MeetingsInOneRoom {

    public int maxMeetings(int[] start, int[] end) {
        int n = start.length;
        int[][] meetings = new int[n][2];

        // Step 1: Create array of (start, end) pairs
        for (int i = 0; i < n; i++) {
            meetings[i][0] = start[i];
            meetings[i][1] = end[i];
        }

        // Step 2: Sort meetings by their end time
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        // Step 3: Greedily select non-overlapping meetings
        int count = 1;        // We always select the first meeting
        int prev = 0;         // Index of last selected meeting

        for (int i = 1; i < n; i++) {
            if (meetings[i][0] > meetings[prev][1]) {
                count++;
                prev = i;     // Update the last selected meeting
            }
        }

        return count;
    }

    // ✅ Main method for testing
    public static void main(String[] args) {
        N_MeetingsInOneRoom sol = new N_MeetingsInOneRoom();

        int[] start1 = {1, 3, 0, 5, 8, 5};
        int[] end1 = {2, 4, 6, 7, 9, 9};
        System.out.println("Max meetings (Example 1): " + sol.maxMeetings(start1, end1));  // Output: 4

        int[] start2 = {10, 12, 20};
        int[] end2 = {20, 25, 30};
        System.out.println("Max meetings (Example 2): " + sol.maxMeetings(start2, end2));  // Output: 1

        int[] start3 = {1, 2};
        int[] end3 = {100, 99};
        System.out.println("Max meetings (Example 3): " + sol.maxMeetings(start3, end3));  // Output: 1
    }
}
