package Greedy.Medium;
import java.util.*;

/*
✅ Problem Statement:
Given a list of page requests, and a cache of limited capacity, count how many page faults occur using the LRU (Least Recently Used) replacement policy.

A page fault occurs when a page is not in memory and must be loaded. If the cache is full, the least recently used page is removed.

✅ Intuition:
- Use a HashSet to store pages currently in memory.
- Use a HashMap to store the most recent index (timestamp) when each page was accessed.
- On each page access:
  → If page not in cache (page fault):
      → If space available, add it.
      → Else, remove LRU page and add new page.
  → If page already in cache, update its access timestamp.
*/

class PageFault {
    static int pageFaults(int n, int capacity, int pages[]) {
        HashSet<Integer> s = new HashSet<>(capacity);
     
        // To store least recently used indexes
        // of pages.
        HashMap<Integer, Integer> indexes = new HashMap<>();
     
        // Start from initial page
        int page_faults = 0;
        for (int i=0; i<n; i++)
        {
            // Check if the set can hold more pages
            if (s.size() < capacity)
            {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);
     
                    // increment page fault
                    page_faults++;
                }
     
                // Store the recently used index of
                // each page
                indexes.put(pages[i], i);
            }
     
            // If the set is full then need to perform lru
            // i.e. remove the least recently used page
            // and insert the current page
            else
            {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i]))
                {
                    // Find the least recently used pages
                    // that is present in the set
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
                    
                    Iterator<Integer> itr = s.iterator();
                    
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru)
                        {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                
                    // Remove the indexes page
                    s.remove(val);
                   //remove lru from hashmap
                   indexes.remove(val);
                    // insert the current page
                    s.add(pages[i]);
     
                    // Increment page faults
                    page_faults++;
                }
     
                // Update the current page index
                indexes.put(pages[i], i);
            }
        }
     
        return page_faults;
    }

    /*
    ✅ Time Complexity:
    - Worst case O(N * C), where N is number of pages and C is capacity (for LRU scan)

    ✅ Space Complexity:
    - O(C) for HashSet and HashMap
    */

    // ✅ Main method for testing
    public static void main(String[] args) {
        int[] pages1 = {5, 0, 1, 3, 2, 4, 1, 0, 5};
        int N1 = 9, C1 = 4;
        System.out.println(pageFaults(N1, C1, pages1)); // Output: 8

        int[] pages2 = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int N2 = 12, C2 = 2;
        System.out.println(pageFaults(N2, C2, pages2)); // Output: 12
    }
}
