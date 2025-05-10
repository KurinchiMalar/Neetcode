package HackerRankPractice.PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/contiguous-array/
https://www.youtube.com/watch?v=xbMpRtZdYdk
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.



Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Example 3:

Input: nums = [0,1,1,1,1,1,0,0,0]
Output: 6
Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class ContiguousArray {

    /*
    TC : O(n)
    SC : O(1)
     */
    public int findMaxLength(int[] nums) {

        /*
        every 0 leads to decrement in sumSoFar by 1
        every 1 leads to increment in sumSoFar by 1

        Keep adding sumsoFar and corresponding index in map.
        When you get the same sum again, (In this case only sum 0 can repeat.) In that case alone update the maxLen of subarray accordingly.
        */
        int sumSoFar = 0;
        int maxLen = 0;
        Map<Integer,Integer> sumToIndexMap = new HashMap();
        sumToIndexMap.put(0,-1); // 0 sum initially at -1th index (preinitialize)
        for( int  i = 0 ; i < nums.length; i++){
            if(nums[i] != 0 && nums[i] != 1) return -1;

            sumSoFar = (nums[i] == 0) ? sumSoFar - 1 : sumSoFar + 1;

            if(!sumToIndexMap.containsKey(sumSoFar)){
                sumToIndexMap.put(sumSoFar, i);
            }else{
                maxLen = Math.max(maxLen, i-sumToIndexMap.get(sumSoFar));
            }
        }
        return maxLen;
        // if there is no valid subarray the sum will never repeat and independent keys will keep on getting added in map.
        // and the maxLen 0 is returned as such.

    }

    public static void main(String[] args) {
        ContiguousArray ob = new ContiguousArray();
        System.out.println(ob.findMaxLength(new int[]{0,1}));
        System.out.println(ob.findMaxLength(new int[]{0,1,0}));
        System.out.println(ob.findMaxLength(new int[]{0,1,1,1,1,1,0,0,0}));

    }
}
