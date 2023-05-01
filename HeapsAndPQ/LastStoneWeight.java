package HeapsAndPQ;
import java.util.Collections;
import java.util.PriorityQueue;
/*
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.



Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation:
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
Example 2:

Input: stones = [1]
Output: 1


Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 1000


 */
/*
TC : O(NlogN) ... logN heapify and  N times worstcase compute and reheapify
SC : O(N)
 */
public class LastStoneWeight {

    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    public int lastStoneWeight(int[] stones) {
        for(int stone: stones){
            pq.add(stone);
        }

        while(!pq.isEmpty() && pq.size() > 1){
            int n1 = pq.poll();
            int n2 = pq.poll();
            if(n1 > n2){
                pq.add(n1-n2);
            }else if(n2 > n1){
                pq.add(n2-n1);
            }
        }
        if(pq.size() == 0){
            return 0;
        }
        return pq.peek();
    }

    public static void main(String[] args){
        LastStoneWeight lsw = new LastStoneWeight();
        System.out.println(lsw.lastStoneWeight(new int[]{2,7,4,1,8,1}));
        lsw.pq.clear();
        System.out.println(lsw.lastStoneWeight(new int[]{1}));

    }
}
