/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
import java.util.*;
public class LongestConsecutiveSequence {


    /*
    https://leetcode.com/problems/longest-consecutive-sequence/submissions/ "Time Limit Exceeded"
    Time Complexity : O(n + k) = O(n) where k = n in worst case (whole array is longest sequence)
    Space Complexity : O(n)
     */
    public static int longestConsecutive(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int num:nums){
            map.put(num,0);
        }
        int maxSeqLen = 1;
        int sourceArLen = nums.length;
        if(sourceArLen == 0){
            return 0;
        }
        if(sourceArLen == 1){
            return 1;
        }
        for(int i=0; i < sourceArLen; i++){
            int curSeqLen = 1;
            for(int iter=1; iter < sourceArLen; iter++){
                if(!map.containsKey(nums[i]+iter)){
                    break;
                }
                curSeqLen++;
            }
            maxSeqLen = curSeqLen > maxSeqLen ? curSeqLen:maxSeqLen;
        }
        return maxSeqLen;
    }
    public static void main(String[] args){
        int[] nums = {0,-1};
        System.out.println("Longest Consecutive Sequence length is : "+ longestConsecutive(nums));
    }
}

