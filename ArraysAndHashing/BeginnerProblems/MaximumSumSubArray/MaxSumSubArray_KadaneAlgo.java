package ArraysAndHashing.BeginnerProblems.MaximumSumSubArray;

import java.util.Arrays;

public class MaxSumSubArray_KadaneAlgo {

    /*
    TC : O(n)
    SC : O(1)
    https://leetcode.com/problems/maximum-subarray/description/

    Additional tweak to question:
    Find the sum of the subarray (including empty subarray) having maximum sum among all subarrays.
    The sum of an empty subarray is 0.

    https://www.youtube.com/watch?v=AHZpyENo7k4

     */
    public int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for(int i = 0; i < nums.length;i++){
            curSum += nums[i];

            maxSum = Integer.max(maxSum,curSum);
            curSum = Math.max(curSum, 0);  // curSum if negative need not carry forward because that is anyway going to impact our window sum. Therefore ignore this and make it 0. (we are looking for a new window now)
        }
        return Math.max(maxSum, 0); // for empty subarray.
    }

    /*
    TC : O(n)
    SC: O(1)
     */
    public int[] getMaxSubArray(int[] nums){
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        int curWindowStart = 0;
        int ansStart = -1;
        int ansEnd = -1;
        for(int i = 0; i < nums.length;i++){
            if(curSum == 0) {
                // we are initiating a new window
                curWindowStart = i;
            }
            curSum += nums[i];

            //maxSum = Integer.max(maxSum,curSum);
            if(curSum > maxSum){  // this is a potential answer window so update the ans indices
                maxSum = curSum;
                ansStart = curWindowStart;
                ansEnd = i;
            }
            curSum = Math.max(curSum, 0);
        }
        return Arrays.copyOfRange(nums,ansStart,ansEnd+1);
    }

    public static void main(String[] args) {
        MaxSumSubArray_KadaneAlgo ob = new MaxSumSubArray_KadaneAlgo();
        System.out.println(ob.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(ob.maxSubArray(new int[]{5,4,-1,7,8}));
        System.out.println(ob.maxSubArray(new int[]{-1}));  // if in question , return 0 if there is no maxSumSubArray

        System.out.println(Arrays.toString(ob.getMaxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})));
        System.out.println(Arrays.toString(ob.getMaxSubArray(new int[]{5,4,-1,7,8})));

    }

}
