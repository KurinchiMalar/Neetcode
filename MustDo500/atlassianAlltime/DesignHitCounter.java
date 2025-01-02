package MustDo500.atlassianAlltime;

//import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/*
https://www.geeksforgeeks.org/design-a-hit-counter/
https://www.naukri.com/code360/problems/hit-counter_1230785

hit(timestamp) – Shows a hit at the given timestamp.
getHits(timestamp) – Returns the number of hits received in the past 5 minutes (300 seconds) (from currentTimestamp).

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
 */
public class DesignHitCounter {

    static ArrayList<Integer> list = new ArrayList<>();

    Queue<Integer> queue = new LinkedList<>();

    TreeMap<Integer,Integer> treeMap = new TreeMap<>();

    int[] hits = new int[300];
    int[] times = new int[300];

    // Sol 1 : BruteForce - Use ArrayList to store all hits
    //  Record a hit.
    /*
    TC : hit - O(1), getHits() - O(n)
    SC : O(n)
     */
    void hit1(int timestamp) {
        list.add(timestamp);
        System.out.println("Adding :"+timestamp);
    }

    //Return the number of hits in the past 5 minutes.
    /*
    TC : O(1) for hit() and O(n) for getHits
    SC : O(n) for queueu
     */
    int getHits1(int timestamp) {
        int i = 0;
        for(i = 0 ; i < list.size(); i++){
            // position i at 300s before current timestamp.
            if(timestamp - list.get(i) < 300) break;
            // all timestamps that are before 300s from timestamp are excluded using for loop.
        }
        System.out.println("HitCount :"+(list.size()-i));

        return  list.size()-i;

    }
    // Sol 2: Using Queue

    void hit2(int timestamp) {
        queue.offer(timestamp);
        System.out.println("Adding :"+timestamp);

    }

    int getHits2(int timestamp) {

        while(!queue.isEmpty() && timestamp-queue.peek() >= 300){
            queue.poll();
        }
        System.out.println("HitCount :"+queue.size());
        return queue.size();
    }

    // Sol 3: For unordered timestamp.
    /*
    TC : O( 300 ) ===> O(1)
    SC : O(300) ==> O(1) for times and hit array
     */
    void hit3(int timestamp) {
        int shard = timestamp % 300;
        if(times[shard] != timestamp){
            times[shard] = timestamp;
            hits[shard] = 1;
        }else{
            hits[shard]++;
        }
        System.out.println("Adding : "+ timestamp);
    }

    int getHits3(int timestamp) {
        int res = 0;
        for(int i = 0 ; i < 300 ; i++){ // Iterate all the times in  times=s array
            if(timestamp - times[i] < 300){
                res += hits[i];
            }
        }
        System.out.println("HitCount : "+res);
        return res;
    }

    // Sol 4: For unordered timestamp, Usng TreeMap
    /*
    TC : O(Mlog N)
    where M is the wide variety of hits that came about within the remaining 5 minutes
         N is the full variety of hits.

    SC : O(N) # of hits stored in treemap
     */
    void hit4(int timestamp) {
        treeMap.put(timestamp,treeMap.getOrDefault(timestamp,0) + 1);
        System.out.println("Adding : "+timestamp);
    }

    int getHits4(int timestamp) {
        int res = 0;
        int start = timestamp - 300;
        start = Math.max(start, 0);

        // we are interested only in subset < timestamp - 300  , exclude all that is >=
        for(int key :treeMap.tailMap(start,false).keySet() ){
            res += treeMap.get(key);
        }
        System.out.println("HitCount : "+res);
        return res;
    }


    public static void main(String[] args) {
        DesignHitCounter counter = new DesignHitCounter();

        System.out.println("************* Sol 1 - Brute Force **************");
        // ArrayList maintains the order , on getHit(timestamp) position i at timestamp-300s
        counter.hit1(1);
        counter.hit1(2);
        counter.hit1(3);
        counter.getHits1(4);
        counter.hit1(300);
        counter.getHits1(300);
        counter.getHits1(301);
        System.out.println("************* Sol 2 - Using queue **************");
        counter.hit2(1);
        counter.hit2(2);
        counter.hit2(3);
        counter.getHits2(4);
        counter.hit2(300);
        counter.getHits2(300);
        counter.getHits2(301);

        System.out.println("************** Sol 3 - For unordered timestamp input******************************");
        counter.hit3(1);
        counter.hit3(2);
        counter.hit3(3);
        counter.getHits3(4);
        counter.hit3(300);
        counter.getHits3(300);
        counter.getHits3(301);

        System.out.println("************** Sol 4 - Using TreeMap, LinkedHashMap - Most Efficient ******************************");
        counter.hit4(1);
        counter.hit4(2);
        counter.hit4(3);
        counter.getHits4(4);
        counter.hit4(300);
        counter.getHits4(300);
        counter.getHits4(301);

    }
}
