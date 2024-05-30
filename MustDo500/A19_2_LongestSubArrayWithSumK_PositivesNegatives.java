package MustDo500;

import java.util.HashMap;

public class A19_2_LongestSubArrayWithSumK_PositivesNegatives {


    /*
Optimal for Array with Postives and Negatives
https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
TC: O(N)
SC : O(N)
 */
    public  int getLongestSubarray_Hashing(int []nums, int k) {
        int n = nums.length;
        HashMap<Integer,Integer> hmap = new HashMap<>(); // sumSoFar, index
        int maxLen = 0;
        int curSum = 0;
        for(int i = 0 ; i < n; i++ ){
            curSum += nums[i]; // This is x
            if(curSum == k){
                maxLen = Math.max(maxLen,i+1);
            } else if(hmap.containsKey(curSum - k)){ // subArray with sum K is present inclusive of current index
                int subArraySumKLen = i - (hmap.get(curSum-k));
                Math.max(maxLen,subArraySumKLen);
            }else{
                if(!hmap.containsKey(curSum))hmap.put(curSum,i); // to avoid re-updating indicies when elements in original array are 0 and the sum is obtained again and again, we need the left most occurence to get the longest
            }

        }
        return maxLen;
    }
}
