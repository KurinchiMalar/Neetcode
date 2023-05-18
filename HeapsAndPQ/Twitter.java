/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.


Example 1:

Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.


Constraints:

1 <= userId, followerId, followeeId <= 500
0 <= tweetId <= 104
All the tweets have unique IDs.
At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.

 */
/*
get News Feed for 1 user
----------------------
 TC : O(n * log k) , here n = 10 ...10 times we poll from heap.
 SC : O(k)  , k = number of followers a user has.

 postTweet for 1 user
 ---------------------
 TC : O(1)
 SC : O(k) , k = number of tweets a user plans to put   (Tweetlist)

follow/unfollow
---------------
TC: O(1)
SC : O(n) , n = # of users
 */
package HeapsAndPQ;

import java.util.*;

class Twitter {
    private static int timeStamp = 0;

    //utility to find if user exists (id, User)
    private Map<Integer,User> userMap;

    class Tweet{
        public int id;
        public int time;
        public Tweet next;

        public Tweet(int id){
            this.id = id;
            this.time = timeStamp++;
            this.next = null;
        }
    }
    class User{
        public int id;
        public Set<Integer> followed;
        public Tweet tweetHead;

        public User(int id){
            this.id = id;
            this.followed = new HashSet<Integer>();
            follow(id); // first follow itself
            this.tweetHead = null;
        }
        public void follow(int id){
            followed.add(id);
        }
        public void unfollow(int id){
            followed.remove(id);
        }
        //every new post add it to head of tweet list of this user
        public void  postTweet(int id){
            Tweet t = new Tweet(id);
            t.next = tweetHead;
            tweetHead = t;

        }

    }

    public Twitter() {

        userMap = new HashMap<Integer,User>();

    }

    /** Compose a new tweet. */

    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)){
            User user = new User(userId);
            userMap.put(userId,user);
        }
        userMap.get(userId).postTweet(tweetId);
    }

    // Best part of this.
    // first get all tweets lists from one user including itself and all people it followed.
    // Second add all heads into a max heap. Every time we poll a tweet with
    // largest time stamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this heap before we get the 10 most recent tweet.
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new LinkedList<>();

        if(!userMap.containsKey(userId)){
            return result;
        }

        Set<Integer> followedUsers = userMap.get(userId).followed;
        PriorityQueue<Tweet> pq = new PriorityQueue<>(followedUsers.size(),(a,b)->(b.time-a.time)); // constant size heap, ordered based on most recent time.
        for(int followedUser:followedUsers){
            Tweet th = userMap.get(followedUser).tweetHead;
            // very imporant! If we add null to the head we are screwed.
            if(th != null){
                pq.add(th);
            }
        }

        int n = 0;
        while(!pq.isEmpty() && n < 10){
            Tweet t = pq.poll();
            result.add(t.id);
            n++;

            if(t.next != null){
                pq.add(t.next);
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)){
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if(!userMap.containsKey(followeeId)){
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId==followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }

    public static void main(String[] args){
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }
}
