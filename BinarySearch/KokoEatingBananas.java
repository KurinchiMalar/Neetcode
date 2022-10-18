/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
 */
/*
Time Complexity : O(log(max(p)) * p)     Brute force where we need to compute hours for every value of k from 1 to max(pile).= O(max(p) * p)
Space Complexity : O(1)
 */
package BinarySearch;
import java.util.Arrays;
import java.lang.Math.*;
public class KokoEatingBananas {

    public static int minEatingSpeed(int[] piles, int h) {

        /*

          1)  h >= len(p) ... atmost 1 pile takes 1 hour.
          2) values of k ranges from 1 to maxValue(piles)

        */
        int left = 1 ;
        int right = Arrays.stream(piles).max().getAsInt();
        int result = Integer.MAX_VALUE;
        int hours = 0;
        while(left <= right){
            int k = (left + right) / 2;
            // compute the hours taken to eat at the rate of k bananas.
            for(int val:piles){
                //hours += Math.ceil(val/k);
                hours += val/k;
                if(val % k != 0){  // for cases like 3/6 , 7/6
                    hours++;
                }
            }
            if(hours <= h){
                result = Integer.min(result,k);
                right = k-1;
            }else{
                left = k+1;
            }
            hours = 0; //reintialize
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(minEatingSpeed(new int[]{3,6,7,11},8));
    }
}
