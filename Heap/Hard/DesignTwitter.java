package Heap.Hard;

import java.util.*;

public class DesignTwitter {

    // ✅ Internal tweet structure with timestamp for ordering
    private static class Tweet {
        int id;
        int time;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    // Global timestamp that increases with each new tweet
    private static int timestamp = 0;

    // Maps userId -> list of that user's tweets
    Map<Integer, List<Tweet>> userTweets;

    // Maps userId -> set of users they follow
    Map<Integer, Set<Integer>> followMap;

    // ✅ Constructor: initializes user tweet and follow maps
    public DesignTwitter() {
        userTweets = new HashMap<>();
        followMap = new HashMap<>();
    }

    /**
     * ✅ postTweet(userId, tweetId)
     * Posts a tweet on behalf of the given user
     */
    public void postTweet(int userId, int tweetId) {
        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(new Tweet(tweetId, timestamp++));
    }

    /**
     * ✅ getNewsFeed(userId)
     * Returns the 10 most recent tweet IDs in the user’s news feed.
     * News feed includes tweets from:
     * - The user themself
     * - People they follow
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();

        // Min-heap to keep top 10 most recent tweets (smallest timestamp on top)
        PriorityQueue<Tweet> minHeap = new PriorityQueue<>((a, b) -> a.time - b.time);

        // Add self to followee list
        Set<Integer> followees = followMap.getOrDefault(userId, new HashSet<>());
        followees.add(userId);

        // Loop through all followees and add their tweets to the heap
        for (int followeeId : followees) {
            List<Tweet> tweets = userTweets.getOrDefault(followeeId, new ArrayList<>());
            for (Tweet tweet : tweets) {
                minHeap.offer(tweet);
                if (minHeap.size() > 10) {
                    minHeap.poll();  // remove oldest tweet to keep size 10
                }
            }
        }

        // Extract from minHeap to get tweets in oldest-to-newest order
        List<Integer> temp = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            temp.add(minHeap.poll().id);
        }

        // Reverse to get newest-to-oldest order
        for (int i = temp.size() - 1; i >= 0; i--) {
            res.add(temp.get(i));
        }

        return res;
    }

    /**
     * ✅ follow(followerId, followeeId)
     * User `followerId` starts following `followeeId`
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;

        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    /**
     * ✅ unfollow(followerId, followeeId)
     * User `followerId` stops following `followeeId`
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;

        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }

    // ✅ main() for dry run & testing
    public static void main(String[] args) {
        DesignTwitter twitter = new DesignTwitter();

        twitter.postTweet(1, 5);  // User 1 posts tweet ID 5
        System.out.println(twitter.getNewsFeed(1));  // [5]

        twitter.follow(1, 2);     // User 1 follows user 2
        twitter.postTweet(2, 6);  // User 2 posts tweet ID 6
        System.out.println(twitter.getNewsFeed(1));  // [6, 5]

        twitter.unfollow(1, 2);   // User 1 unfollows user 2
        System.out.println(twitter.getNewsFeed(1));  // [5]
    }
}

/*

✅ Problem Explanation:
- Each user can:
  - Post tweets
  - Follow/unfollow others
  - Retrieve a feed of recent tweets (from self + followees)

✅ Dry Run:
Example:
- User 1 posts 5 → feed: [5]
- User 1 follows 2 → User 2 posts 6 → feed: [6, 5]
- User 1 unfollows 2 → feed: [5]

✅ Intuition:
- Store tweets with timestamps
- Use a min-heap to track the most recent 10 tweets
- Always include self in followees for feed

✅ Algorithm:
1. Track each user's tweets and followees
2. For `getNewsFeed()`:
   - Collect tweets from followees (including self)
   - Use min-heap to maintain top 10 tweets by timestamp
   - Reverse final list for most recent first

✅ Time Complexity:
- postTweet: O(1)
- follow/unfollow: O(1)
- getNewsFeed: O(N log 10) ≈ O(N), where N = total tweets from followees

✅ Space Complexity:
- O(U + T)
  - U = number of users
  - T = number of tweets

*/
