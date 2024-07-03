package MustDo500.atlassianAlltime;
import java.util.*;
/*
https://leetcode.com/problems/last-stone-weight/
 */
/*
My submission: https://leetcode.com/problems/last-stone-weight/submissions/
TC : O(nlog n)
SC : O(n)
 */
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {

        if(stones == null || stones.length == 0){
            return -1;
        }
        if(stones.length == 1) return stones[0];
        //Integer[] stonesAr = Arrays.stream(stones).boxed().toArray(Integer[]::new);
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());

        for(int stone: stones){ //---------------------------------O(n)
            pq.add(stone);
        }
        while(!pq.isEmpty() && pq.size() >= 2){
            int x = pq.poll(); //---------------------------------O(log n)
            int y = pq.poll();

            if(x != y){
                pq.add(x-y); // x will be greater always because of maxHeap.
            }
        }
        return ((pq.isEmpty()) ? 0:pq.poll());
    }

    public static void main(String[] args) {
        LastStoneWeight ob = new LastStoneWeight();

        //System.out.println(ob.lastStoneWeight(new int[]{2,7,4,1,8,1}));
        System.out.println(ob.lastStoneWeight(new int[]{1,3}));
        System.out.println(ob.lastStoneWeight(new int[]{7,6,7,6,9}));

        /*9,7,7,6,6
2 , 7,6,6
2 , 1, 6
2, 5
3*/


    }
}
